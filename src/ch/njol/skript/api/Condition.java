/*
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Copyright 2011, 2012 Peter Güttinger
 * 
 */

package ch.njol.skript.api;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.api.intern.TopLevelExpression;
import ch.njol.skript.lang.ExprParser;
import ch.njol.skript.lang.SimpleVariable;
import ch.njol.util.Checker;

/**
 * A condition which must be fulfilled for the trigger to continue. If the condition is in a section the behaviour depends on the section.
 * 
 * @author Peter Güttinger
 * @see Skript#addCondition(Class, String...)
 */
public abstract class Condition extends TopLevelExpression {
	
	private boolean negated = false;
	
	protected Condition() {}
	
	/**
	 * Checks whether this condition is satisfied with the given event. This should not alter the event or the world in any way, as conditions are only checked until one returns
	 * false. All subsequent conditions of the same trigger will then be omitted.<br/>
	 * <br/>
	 * You might want to use {@link SimpleVariable#check(Event, Checker)}
	 * 
	 * @param e the event to check
	 * @return <code>true</code> if the condition is satisfied, <code>false</code> otherwise or if the condition doesn't apply to this event.
	 */
	@Override
	public abstract boolean run(final Event e);
	
	/**
	 * Sets the negation status of this condition. This will change the behaviour of {@link SimpleVariable#check(Event, Checker, Condition)}.
	 * 
	 * @param invert
	 */
	protected final void setNegated(final boolean invert) {
		negated = invert;
	}
	
	/**
	 * 
	 * @return whether this condition is negated or not.
	 */
	public final boolean isNegated() {
		return negated;
	}
	
	public static Condition parse(final String s) {
		return (Condition) ExprParser.parse(s, Skript.getConditions().iterator(), false);
	}
	
}
