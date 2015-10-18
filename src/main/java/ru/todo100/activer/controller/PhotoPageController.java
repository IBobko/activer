
package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/photo")
public class PhotoPageController
{
	@RequestMapping("")
	void crop() {
		System.out.println("CROP!!!");
	}
}
