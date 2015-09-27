/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 *
 * NOVARDIS
 * Detskaya st. 5A, 199106 
 * Saint Petersburg, Russian Federation 
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 *
 * (c) 2015 by NOVARDIS
 *********************************************************************/
package ru.todo100.activer.populators;

/**
 * @author igor
 * @package ru.todo100.activer.populators
 * @link http://www.novardis.com/
 * @copyright 2015 NOVARDIS
 */
public interface Populator<SOURCE,TARGET>
{
	public TARGET populate(SOURCE source);
}
