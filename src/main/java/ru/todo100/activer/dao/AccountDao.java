package ru.todo100.activer.dao;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.todo100.activer.data.*;
import ru.todo100.activer.form.FriendSearchForm;
import ru.todo100.activer.form.RegisterForm;
import ru.todo100.activer.model.*;
import ru.todo100.activer.populators.*;
import ru.todo100.activer.qualifier.AccountQualifier;
import ru.todo100.activer.qualifier.Qualifier;
import ru.todo100.activer.service.PhotoService;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


class ProfileValue {
    private String name;
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

@SuppressWarnings(value = {"unused", "SqlResolve", "unchecked"})
@Transactional
public class AccountDao extends AbstractDao {
    @Autowired
    PhotoService photoService1;
    @Autowired
    private ReferService referService;
    @Autowired
    private PromoService promoService;
    @Autowired
    private TripPopulator tripPopulator;
    @Autowired
    private InterestPopulator interestPopulator;
    @Autowired
    private DreamPopulator dreamsPopulator;
    @Autowired
    private ProfilePopulator profilePopulator;
    @Autowired
    private EducationPopulator educationPopulator;
    @Autowired
    private JobPopulator jobPopulator;
    @Autowired
    private ChildrenPopulator childrenPopulator;

    private HashMap<Integer, List<ProfileValue>> synchronizers = new HashMap<>();

    public List<AccountItem> getAll() {
        return getCriteria().setMaxResults(10).list();
    }

    @Override
    public Class<AccountItem> getItemClass() {
        return AccountItem.class;
    }

    public AccountItem get(Integer id) {
        return getSession().get(this.getItemClass(), id);
    }

    public void save(final AccountItem account) {
        final Session session = getSession();
        session.saveOrUpdate(account);
    }

    public AccountItem get(String login) {
        return (AccountItem) getCriteria().add(Restrictions.eq("username", login)).uniqueResult();
    }

    public AccountItem getByEmail(String email) {
        return (AccountItem) getCriteria().add(Restrictions.eq("email", email)).uniqueResult();
    }

    public AccountItem getCurrentAccountForProfile() {
        getSession().enableFetchProfile("account-for-profile");
        AccountItem accountItem = getCurrentAccount();
        getSession().disableFetchProfile("account-for-profile");
        return accountItem;
    }

    public AccountItem getCurrentAccount() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {

            for (final GrantedAuthority authority : auth.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
                    return null;
                }
            }

            final AccountItem accountItem = get(auth.getName());
            accountItem.setLastActivity(new GregorianCalendar());
            save(accountItem);
            return accountItem;
        }
        return null;
    }

    public void addSynchronizer(final Integer accountId, final String name, final Object value) {
        final List<ProfileValue> profileValues;
        if (synchronizers.containsKey(accountId)) {
            profileValues = synchronizers.get(accountId);
        } else {
            profileValues = new ArrayList<>();
            synchronizers.put(accountId, profileValues);
        }
        final ProfileValue profileValue = new ProfileValue();
        profileValue.setName(name);
        profileValue.setValue(value);
        profileValues.add(profileValue);
    }

    public void initCurrentProfile(HttpSession session) {
        final AccountItem account = getCurrentAccountForProfile();
        final ProfileData profile = profilePopulator.populate(account);
        profile.setMy(true);
        session.setAttribute("currentProfileData", profile);
    }

    public ProfileData getCurrentProfileData(HttpSession session) {
        if (session.getAttribute("currentProfileData") == null) {
            initCurrentProfile(session);
        }
        final ProfileData profileData = (ProfileData) session.getAttribute("currentProfileData");
        if (synchronizers.containsKey(profileData.getId())) {
            final List<ProfileValue> values = synchronizers.get(profileData.getId());
            for (ProfileValue value : values) {
                if (value.getName().equals("balance")) {
                    profileData.setBalance((BigDecimal) value.getValue());
                }
                if (value.getName().equals("showOnline")) {
                    profileData.setShowOnline((Boolean) value.getValue());
                }
                if (value.getName().equals("showPremium")) {
                    profileData.setShowPremium((Boolean) value.getValue());
                }
                if (value.getName().equals("trips")) {
                    final List<TripData> trips = new ArrayList<>();
                    for (TripItem item : (Set<TripItem>) value.getValue()) {
                        trips.add(tripPopulator.populate(item));
                    }
                    profileData.setTrips(trips);
                }
                if (value.getName().equals("interests")) {
                    final List<InterestData> interests = new ArrayList<>();
                    for (InterestItem item : (Set<InterestItem>) value.getValue()) {
                        interests.add(interestPopulator.populate(item));
                    }
                    profileData.setInterests(interests);
                }
                if (value.getName().equals("dreams")) {
                    final List<DreamData> dreams = new ArrayList<>();
                    for (DreamItem item : (Set<DreamItem>) value.getValue()) {
                        dreams.add(dreamsPopulator.populate(item));
                    }
                    profileData.setDreams(dreams);
                }

                if (value.getName().equals("firstName")) {
                    profileData.setFirstName((String)value.getValue());
                }

                if (value.getName().equals("lastName")) {
                    profileData.setLastName((String)value.getValue());
                }

                if (value.getName().equals("sex")) {
                    profileData.setSex((Integer) value.getValue());
                }

                if (value.getName().equals("maritalStatus")) {
                    profileData.setMaritalStatus((Integer)value.getValue());
                }
                /*TODO find best solution.*/
                if (value.getName().equals("birthdate")) {
                    profileData.setBirthDate(Facade.FORMAT_DD_MM_yyyy.format(((Calendar)value.getValue()).getTime()));
                    profileData.setZodiac(profilePopulator.zodiac(((Calendar)value.getValue()).get(Calendar.MONTH)+1,((Calendar)value.getValue()).get(Calendar.DAY_OF_MONTH)));
                }


                if (value.getName().equals("education")) {
                    profileData.setEducation(educationPopulator.populate((EducationItem)value.getValue()));
                }

                if (value.getName().equals("job")) {
                    profileData.setJob(jobPopulator.populate((JobItem) value.getValue()));
                }

                if (value.getName().equals("children")) {
                    List<ChildrenData> childrenDataList = new ArrayList<>();
                    for (ChildrenItem children: (Set<ChildrenItem>) value.getValue()) {
                        childrenDataList.add(childrenPopulator.populate(children));
                    }
                    if (!childrenDataList.isEmpty()) {
                        profileData.setChildren(childrenDataList.get(0));
                    }
                }
            }
            synchronizers.remove(profileData.getId());
        }
        return profileData;
    }

    public void addFriend(AccountItem account, Integer friendId) {
        account.getFriends().add(get(friendId));
        save(account);
    }

    public void deleteOldInterests() {
        getSession().createSQLQuery("DELETE FROM INTEREST WHERE account_id is null").executeUpdate();
    }

    public AccountItem saveForm(RegisterForm registerForm) throws InputError {
        String password = RandomStringUtils.randomAlphanumeric(6);
        InputError ie = new InputError();

        if (registerForm.getEmail().equals("")) {
            ie.addError("E-mail is empty");
        }

        if (!MailService.isValidEmailAddress(registerForm.getEmail())) {
            ie.addError("E-mail is invalid");
        }

        AccountItem exists = this.get(registerForm.getEmail());
        if (exists != null) {
            ie.addError("Login is busy");
        }

        if (registerForm.getFirstName().trim().equals("")) {
            ie.addError("First name is empty");
        }

        if (registerForm.getLastName().trim().equals("")) {
            ie.addError("Last name is empty");
        }

        PromoCodeItem promoCode = null;
        if (StringUtils.isNotEmpty(registerForm.getPromo())) {
            promoCode = promoService.getPromo(registerForm.getPromo());
            if (promoCode != null) {
                if (promoCode.getUsed() != null) {
                    ie.addError("Promo is used");
                }
            } else {
                ie.addError("Promo is invalid");
            }
        }

        if (!ie.getErrors().isEmpty()) {
            throw ie;
        }

        final AccountItem account = new AccountItem();
        account.setEmail(registerForm.getEmail());
        account.setUsername(registerForm.getEmail());
        account.setPassword(password);
        account.setFirstName(registerForm.getFirstName());
        account.setLastName(registerForm.getLastName());
        account.addRole("ROLE_USER");
        account.setCreatedDate(new GregorianCalendar());
        account.setReferCode(RandomStringUtils.randomAlphanumeric(6));

        final AccountItem referAccount = referService.getUserByRefer(registerForm.getRefer());
        if (referAccount != null) {
            account.setUsedReferCode(referAccount.getReferCode());
        }
        save(account);
        if (promoCode != null) {
            promoCode.setUsed(account);
            getSession().save(promoCode);
        }
        return account;
    }

    public void deleteTrip(Integer id) {
        final AccountItem account = getCurrentAccount();
        for (TripItem trip : account.getTripItems()) {
            if (Objects.equals(trip.getId(), id)) {
                account.getTripItems().remove(trip);
                break;
            }
        }
        addSynchronizer(account.getId(), "trips", account.getTripItems());
    }

    public AccountItem getRandomOnlineAccount(HttpSession session) {
        ProfileData profileData = getCurrentProfileData(session);

        final List<BigDecimal> result = getSession().createSQLQuery(
                "select id from (select id,extract( day from diff )*24*60*60*1000 + " +
                        " extract( hour from diff )*60*60*1000 + " +
                        " extract( minute from diff )*60*1000 + " +
                        " round(extract( second from diff )*1000) total_milliseconds " +
                        "from (select id,(systimestamp - ACCOUNT_LAST_ACTIVITY) diff from ACCOUNT)) where total_milliseconds < 10906720 and id !=" + profileData.getId()).list();

        final Integer index = ThreadLocalRandom.current().nextInt(0, result.size());
        return getSession().load(AccountItem.class, result.get(index).intValue());
    }

    public void deleteDream(Integer id) {
        final AccountItem account = getCurrentAccount();
        for (DreamItem dream : account.getDreamItems()) {
            if (Objects.equals(dream.getId(), id)) {
                account.getDreamItems().remove(dream);
                break;
            }
        }
    }

    public List<FriendData> getByQualifier(AccountQualifier qualifier) {
        if (qualifier.getFriendSearchForm().isNull()) {
            return new ArrayList<>();
        }
        Criteria criteria = getCriteria();
        if (qualifier.getStart() != null) {
            criteria.setFirstResult(qualifier.getStart());
        }

        if (qualifier.getCount() != null) {
            criteria.setMaxResults(qualifier.getCount());
        }

        final FriendSearchForm friendSearchForm = qualifier.getFriendSearchForm();
        if (StringUtils.isNotEmpty(friendSearchForm.getEmail())) {
            criteria.add(Restrictions.eq("email", friendSearchForm.getEmail()));
        }

        if (friendSearchForm.getSex() != null) {
            criteria.add(Restrictions.eq("sex", friendSearchForm.getSex()));
        }

        if (StringUtils.isNotEmpty(friendSearchForm.getFirstName())) {
            criteria.add(Restrictions.eq("firstName", friendSearchForm.getFirstName()));
        }

        if (StringUtils.isNotEmpty(friendSearchForm.getLastName())) {
            criteria.add(Restrictions.eq("lastName", friendSearchForm.getLastName()));
        }

        if (StringUtils.isNotEmpty(friendSearchForm.getBirthDay())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
            try {
                Date date = simpleDateFormat.parse(friendSearchForm.getBirthDay());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                criteria.add(Restrictions.eq("birthdate", calendar));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        final List<AccountItem> result = criteria.list();

        final List<FriendData> datas = new ArrayList<>();
        for (AccountItem item : result) {
            FriendData friendData = new FriendData();
            friendData.setFirstName(item.getFirstName());
            friendData.setLastName(item.getLastName());
            friendData.setId(item.getId());

            JobData jobData = new JobData();

            if (item.getJobItems() != null && !item.getJobItems().isEmpty()) {
                JobItem jobItem = (JobItem) item.getJobItems().toArray()[item.getJobItems().size() - 1];
                jobData.setCity(jobItem.getCity());
                jobData.setPost(jobItem.getPost());
                jobData.setWork(jobItem.getWorkplace());
            }
            friendData.setJob(jobData);

            PhotoAvatarSizeData sized = photoService1.getSizedPhoto(item.getId());
            if (sized != null) {
                friendData.setPhoto60x60(sized.getPhotoMini());
            }
            datas.add(friendData);
        }

        return datas;
    }

    public Long getCountByQualifier(Qualifier qualifier) {
        final Criteria criteria = getCriteria();
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
