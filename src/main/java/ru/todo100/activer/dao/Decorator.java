/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 * <p>
 * NOVARDIS
 * Detskaya st. 5A, 199106
 * Saint Petersburg, Russian Federation
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 * <p>
 * (c) 2015 by NOVARDIS
 *********************************************************************/
package ru.todo100.activer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.todo100.activer.model.IWantItem;

/**
 *
 *
 * @author igor
 * @package ru.todo100.activer.service
 * @link http://www.novardis.com/
 * @copyright 2015 NOVARDIS
 */
public class Decorator
{
	@Autowired
	IWantDao iWantService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void f() {
		final IWantItem iWantModel = iWantService.get(10);
	}
}
