package com.andgdx.menu;

import com.andgdx.entity.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/** @author Krustnic */
public class HorizontalSlidingPane extends Entity {
	private float sectionWidth, sectionHeight;
	// Container for the sections
	private Group sections;
	private float amountX = 0;
	// direction of section movement
	private int transmission = 0;
	private float stopSection = 0;
	private float speed = 1500;
	private int currentSection = 1;
	// velocity in pixel per second after which we assume the user wants to slide to
	// the next page
	private float flingSpeed = 1000;
	private float overscrollDistance = 500;
	private Rectangle cullingArea = new Rectangle();
	private Actor touchFocusedChild;
	private ActorGestureListener actorGestureListener;

	public HorizontalSlidingPane() {
		this(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics()
				.getHeight());
	}

	public HorizontalSlidingPane(float width, float height) {
		sections = new Group();
		this.addActor(sections);
		sectionWidth = width;
		sectionHeight = height;
		this.setBounds(0, 0, sectionWidth, sectionHeight);
		actorGestureListener = new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count,
					int button) {
			}

			@Override
			public void pan(InputEvent event, float x, float y, float deltaX,
					float deltaY) {
				if (amountX < -overscrollDistance)
					return;
				if (amountX > (sections.getChildren().size - 1) * sectionWidth
						+ overscrollDistance)
					return;
				amountX -= deltaX;
				cancelTouchFocusedChild();
			}

			@Override
			public void fling(InputEvent event, float velocityX,
					float velocityY, int button) {
				if (Math.abs(velocityX) > flingSpeed) {
					if (velocityX > 0)
						setStopSection(currentSection - 2);
					else
						setStopSection(currentSection);
				}
				cancelTouchFocusedChild();
			}

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				if (event.getTarget().getClass() == Entity.class) {
					touchFocusedChild = event.getTarget();
				}
			}
		};
		this.addListener(actorGestureListener);
	}

	public void addSection(Actor widget) {
		widget.setX(this.sections.getChildren().size * sectionWidth);
		widget.setY(0);
		widget.setWidth(sectionWidth);
		widget.setHeight(sectionHeight);
		sections.addActor(widget);
	}

	public int calculateCurrentSection() {

		int section = Math.round(amountX / sectionWidth) + 1;

		if (section > sections.getChildren().size)
			return sections.getChildren().size;
		if (section < 1)
			return 1;
		return section;
	}

	public int getSectionsCount() {
		return sections.getChildren().size;
	}

	public void setStopSection(int stoplineSection) {
		if (stoplineSection < 0)
			stoplineSection = 0;
		if (stoplineSection > this.getSectionsCount() - 1)
			stoplineSection = this.getSectionsCount() - 1;
		stopSection = stoplineSection * sectionWidth;

		// checking direction of movement
		// transmission == 1 - to the right
		// transmission == -1 - to the left
		if (amountX < stopSection) {
			transmission = 1;
		} else {
			transmission = -1;
		}
	}

	private void move(float delta) {
		// checking for direction of movement
		if (amountX < stopSection) {

			// Movement is to the right
			// if we are here and moved to the left
			// this means we are at the point where we want to stop
			if (transmission == -1) {
				amountX = stopSection;
				// saving current section number
				currentSection = calculateCurrentSection();
				return;
			}
			// setting movement
			amountX += speed * delta;
		} else if (amountX > stopSection) {
			if (transmission == 1) {
				amountX = stopSection;
				currentSection = calculateCurrentSection();
				return;
			}
			amountX -= speed * delta;
		}
	}

	@Override
	public void act(float delta) {

		// moving container with sections
		sections.setX(-amountX);
		cullingArea.set(-sections.getX() + 50, sections.getY(),
				sectionWidth - 100, sectionHeight);
		sections.setCullingArea(cullingArea);
		// if we move our finger on the display
		if (actorGestureListener.getGestureDetector().isPanning()) {
			// we set the section to which we want to move.
			setStopSection(calculateCurrentSection() - 1);
		} else {

			// if the finger is far from the display, we animate movement to
			// previous point
			move(delta);
		}
	}

	void cancelTouchFocusedChild() {
		if (touchFocusedChild == null)
			return;
		try {
			this.getStage().cancelTouchFocusExcept(actorGestureListener, this);
		} catch (Exception e) {
		}
		touchFocusedChild = null;
	}

	public void setFlingSpeed(float _flingSpeed) {
		flingSpeed = _flingSpeed;
	}

	public void setSpeed(float _speed) {
		speed = _speed;
	}

	public void setOverscrollDistance(float _overscrollDistance) {
		overscrollDistance = _overscrollDistance;
	}
}