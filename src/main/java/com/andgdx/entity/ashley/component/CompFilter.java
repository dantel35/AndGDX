/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.andgdx.entity.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

/**
 * This is just a convenience wrapper class which uses ashley Family builder implementation!
 * Represents a group of {@link Component}s. It is used to describe what {@link Entity} objects an {@link EntitySystem} should
 * process. Example: {@code Family.all(PositionComponent.class, VelocityComponent.class).get()} Families can't be instantiated
 * directly but must be accessed via a builder ( start with {@code Family.all()}, {@code Family.one()} or {@code Family.exclude()}
 * ), this is to avoid duplicate families that describe the same components.
 * @author Stefan Bachmann (original author of com.badlogic.ashley.core.Family, not this class)
 */
public class CompFilter {
	private static final Builder builder = new Builder();


	public static Family get()
	{
		return builder.get();
	}
	
 

	/**
	 * @param componentTypes entities will have to contain all of the specified components.
	 * @return A Builder singleton instance to get a family
	 */
	@SafeVarargs
	public static final Builder all (Class<? extends Component>... componentTypes) {
		return builder.reset().all(componentTypes);
	}

	/**
	 * @param componentTypes entities will have to contain at least one of the specified components.
	 * @return A Builder singleton instance to get a family
	 */
	@SafeVarargs
	public static final Builder one (Class<? extends Component>... componentTypes) {
		return builder.reset().one(componentTypes);
	}

	/**
	 * @param componentTypes entities cannot contain any of the specified components.
	 * @return A Builder singleton instance to get a family
	 */
	@SafeVarargs
	public static final Builder exclude (Class<? extends Component>... componentTypes) {
		return builder.reset().exclude(componentTypes);
	}

	public static class Builder {
		Class<? extends Component>[] excludes;
		Class<? extends Component>[] ones;
		Class<? extends Component>[] alls;

		
		Builder() {
			
		}
		
		/**
		 * Resets the builder instance
		 * @return A Builder singleton instance to get a family
		 */
		public Builder reset () {
			excludes = null;
			ones = null;
			alls = null;
			return this;
		}

		/**
		 * @param componentTypes entities will have to contain all of the specified components.
		 * @return A Builder singleton instance to get a family
		 */
		@SafeVarargs
		public final Builder all (Class<? extends Component>... componentTypes) {
			alls = componentTypes;
			return this;
		}

		/**
		 * @param componentTypes entities will have to contain at least one of the specified components.
		 * @return A Builder singleton instance to get a family
		 */
		@SafeVarargs
		public final Builder one (Class<? extends Component>... componentTypes) {
			ones = componentTypes;
			return this;
		}

		/**
		 * @param componentTypes entities cannot contain any of the specified components.
		 * @return A Builder singleton instance to get a family
		 */
		@SafeVarargs
		public final Builder exclude (Class<? extends Component>... componentTypes) {
			excludes = componentTypes;
			return this;
		}

		/** @return A family for the configured component types */
		public Family get () {
			Family family = null;
				
				if (alls != null && ones != null && excludes != null && alls.length > 0 && ones.length > 0 && excludes.length > 0)
				{
					family = Family.all(alls).one(ones).exclude(excludes).get();					
				}
				else  if (alls != null && ones != null  && alls.length > 0 && ones.length > 0 )
				{
					family = Family.all(alls).one(ones).get();					
				}
				else  if (alls != null && excludes != null  && alls.length > 0 && excludes.length > 0 )
				{
					family = Family.all(alls).exclude(excludes).get();					
				}
				else  if (excludes != null && ones != null && excludes.length > 0 && ones.length > 0 )
				{
					family = Family.exclude(excludes).one(ones).get();					
				}
				else  if (alls != null && alls.length > 0 )
				{
					family = Family.all(alls).get();					
				}
				else  if (ones != null && ones.length > 0 )
				{
					family = Family.one(ones).get();					
				}
				else  if (excludes != null && excludes.length > 0 )
				{
					family = Family.exclude(excludes).get();					
				}
			return family;
		}
	}

}
