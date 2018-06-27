package org.gradle;

import static org.junit.Assert.*;

import org.junit.Test;

import com.andgdx.animation.Animatable;
import com.andgdx.animation.AnimationConfig;
import com.andgdx.animation.AnimationConfigBag;
import com.andgdx.animation.AnimationMachineState;
import com.andgdx.animation.IAnimationListener;
import com.andgdx.entity.IEntity;

public class AnimationMachineStateTest {
	IAnimationListener listener;
	@Test
	public void test() {
		
		AnimationMachineState s1 = new AnimationMachineState();
		AnimationMachineState s2 = new AnimationMachineState();
		
		s1.addState("running");
		s1.addState("facingDirection:left");
		

		s2.addState("facingDirection:left");
		s2.addState("running");
		
		
		assertTrue(s1.equals(s2));
		assertTrue(s1.isSubset(s2));
		
		s2.addState("jumping");
		assertFalse(s2.isSubset(s1));
		assertFalse(s1.equals(s2));
		
		assertEquals(67,s2.overlap(s1)); //67 because we round up 66,666666666...
		assertEquals(67,s1.overlap(s2));
		
		AnimationMachineState s3 = new AnimationMachineState();
		s3.addState("facingDirection:left");
		s3.addState("running");
		s3.addState("jumping");
		
		assertEquals(100,s2.overlap(s3));
		assertEquals(100,s3.overlap(s2));
		
		s3.addState("jumping");
		
		assertEquals(100,s2.overlap(s3));
		assertEquals(100,s3.overlap(s2));
		
		AnimationMachineState s4 = new AnimationMachineState();
		s4.addState("facingDirection:left");
		
		
		AnimationConfig conf1 = new AnimationConfig() {

			@Override
			public void onPlay(Animatable entity) {
//				SpriterEntity spriter = (SpriterEntity) entity;
//				spriter.setAnimation(2);
				
			}

			@Override
			public void onStop(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPause(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onResume(Animatable entity) {
				// TODO Auto-generated method stub
				
			}
			
		};
		AnimationConfig conf2 = new AnimationConfig() {

			@Override
			public void onPlay(Animatable entity) {
//				SpriterEntity spriter = (SpriterEntity) entity;
//				spriter.setAnimation(2);
				
			}

			@Override
			public void onStop(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPause(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onResume(Animatable entity) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		AnimationConfig conf3 = new AnimationConfig() {

			@Override
			public void onPlay(Animatable entity) {
//				SpriterEntity spriter = (SpriterEntity) entity;
//				spriter.setAnimation(2);
				
			}

			@Override
			public void onStop(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPause(Animatable entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onResume(Animatable entity) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		conf1.name = "test";
		conf2.name = "test2";
		conf3.name = "test3";

		
		AnimationConfigBag bag = new AnimationConfigBag(listener);
		bag.add(s1, conf1);
		
		bag.add(s2, conf2);
		
		bag.add(s4, conf3);
		
		AnimationConfig testConfig = bag.get(s1);
		
		assertEquals(conf1.name, testConfig.name);
		
		testConfig = bag.get(s2);
		
		assertEquals(conf2.name, testConfig.name);
		
		bag.setCurrent(s2);
		
		assertEquals(conf2.name, bag.getCurrent().name);
		
		bag.setCurrent(s1);
		
		assertEquals(conf1.name, bag.getCurrent().name);
		
		bag.setCurrent(s4);
		
		assertEquals(conf3.name, bag.getCurrent().name);
		
		
	}

}
