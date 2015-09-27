package ru.todo100.activer.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import ru.todo100.activer.data.MarkData;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */

public class MarkFacade
{
	public List<String> StringToMarks(String strMarks) {
		return null;
	}

	public String MarksToStrings(List<MarkData> marks) {
		List<String> result = new ArrayList<>();

		for (MarkData mark: marks) {
			result.add(mark.getName());
		}
		return StringUtils.arrayToDelimitedString(result.toArray(),", ");
	}
}
