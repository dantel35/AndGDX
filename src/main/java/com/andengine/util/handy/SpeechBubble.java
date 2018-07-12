package com.andengine.util.handy;

import com.andgdx.entity.shapes.Rectangle;
import com.badlogic.gdx.graphics.Color;

public class SpeechBubble extends Rectangle {
	
	private static int charactersPerSecond = 20;

//	Time lastTouchTimestamp ;
//
//	TickerTextOptions pTickerTextOptions;
//	private TickerText speechBubbleText;
//	VertexBufferObjectManager vertexBufferObjectManager;
//	BaseGameActivity activity;
//	Font font;
//	private Line bottomShadow;
//	private Line sideShadow;
//	Color color_grey = new Color(64f / 255f, 64f / 255f, 64f / 255f);
//	private float textShowingDuration;
//	private long showBubbleStartTime;
//
//	public SpeechBubble(BaseGameActivity activity, Font font,
//			VertexBufferObjectManager vertexBufferObjectManager) {
//
//		super(0, 0, 10, 10, vertexBufferObjectManager);
//		this.vertexBufferObjectManager = vertexBufferObjectManager;
//		this.activity = activity;
//		this.font = font;
//		lastTouchTimestamp = new Time();
//		pTickerTextOptions = new TickerTextOptions(charactersPerSecond);
//		this.setColor(Color.WHITE);
//		float lineWidth = 4f;
//		bottomShadow = new Line(0f, 0f, 1f, 0f, lineWidth,
//				vertexBufferObjectManager);
//		bottomShadow.setColor(color_grey);
//		sideShadow = new Line(0f, 0f, 0f, 1f, lineWidth,
//				vertexBufferObjectManager);
//		sideShadow.setColor(color_grey);
//
//	}
//	
//	public boolean isTextCompletlyDisplayed()
//	{
//		boolean resultValue = false;
//		lastTouchTimestamp.setToNow();
////		float millisNow = lastTouchTimestamp.toMillis(true);
//		long millisNow = System.currentTimeMillis();
//		
//		
//		long difference= millisNow-showBubbleStartTime;
//		
////		Log.d("rivale", "millisNow ist: "+millisNow);
////		Log.d("rivale", "showBubbleStartTime ist: "+showBubbleStartTime);
////		Log.d("rivale", " - difference ist: "+difference);
////		Log.d("rivale", " - textShowingDuration ist: "+textShowingDuration);
//	
//		
//		if(Math.abs(millisNow-showBubbleStartTime) >= textShowingDuration )
//		{
//			if(textShowingDuration!=0)
//			resultValue = true;
//		}
//		
//		return resultValue;
//	}
//
//	public void setSpeed(float charactersPerSecond) {
//		pTickerTextOptions = new TickerTextOptions(charactersPerSecond);
//	}
//
//	public void setFont(Font font) {
//		this.font = font;
//	}
//
//	public void setBackgroundColor(Color color) {
//		this.setColor(color);
//	}
//
//	public void setShadowColor(Color color) {
//		bottomShadow.setColor(color);
//		sideShadow.setColor(color);
//	}
//
//	/**
//	 * Sets the text and the font color of the bubble. This will not 
//	 * actually show the bubble. Call showBubble(...) after you setted the
//	 * Text with this method.
//	 * @param text
//	 * @param color
//	 */
//	public void setText(String text, Color color) {
//
//		float textLaenge = text.length()- text.length()/10f;
//		float charactersPerSecond = pTickerTextOptions.getCharactersPerSecond();
//		textShowingDuration = (textLaenge/charactersPerSecond)*1000f;
//		speechBubbleText = new TickerText(10, 10, font, text, pTickerTextOptions,
//				vertexBufferObjectManager);
//		
//		
//		
//		activity.runOnUpdateThread(new Runnable() {
//			public void run() {
//				
////				if (speechBubbleText.hasParent()) {
////					speechBubbleText.detachSelf();
////				}
//				if (bottomShadow.hasParent() == false) {
//					SpeechBubble.this.attachChild(bottomShadow);
//				}
//				if (sideShadow.hasParent() == false) {
//					SpeechBubble.this.attachChild(sideShadow);
//				}
//			}
//		});
//		
//		
//
//		if (color == null) {
//			color = Color.BLACK;
//		}
//
//		speechBubbleText.setColor(color);
//		this.setWidth(speechBubbleText.getWidth() + 20);
//		this.setHeight(speechBubbleText.getHeight() + 20);
//		sideShadow.setX(this.getWidth());
//		sideShadow.setScaleCenter(0, 0);
//		sideShadow.setScale(1, this.getHeight());
//
//		bottomShadow.setY(this.getHeight());
//		bottomShadow.setScaleCenter(0, 0);
//		bottomShadow.setScale(this.getWidth(), 1);
//		this.setVisible(false);
//
//	}
//
//	private void attachText() {
//		activity.runOnUpdateThread(new Runnable() {
//			public void run() {
//				if (speechBubbleText.hasParent()) {
//					speechBubbleText.detachSelf();
//				}
//				SpeechBubble.this.attachChild(speechBubbleText);
//
//				if (bottomShadow.hasParent() == false) {
//					SpeechBubble.this.attachChild(bottomShadow);
//				}
//				if (sideShadow.hasParent() == false) {
//					SpeechBubble.this.attachChild(sideShadow);
//				}
//			}
//		});
//	}
//	
//	/**
//	 * Shows the bubble with an animation with standard speed 
//	 */
//	public void showBubble()
//	{
//		showBubble(0.25f);
//	}
//	
//	/**
//	 * Shows the bubble with an animation with custom speed.
//	 * @param duration
//	 */
//	public void showBubble(float duration)
//	{
//		Time timestamp = new Time();
//		timestamp.setToNow();
////		showBubbleStartTime = timestamp.toMillis(false);
//		showBubbleStartTime = System.currentTimeMillis();
//		Log.d("rivale", "showBubbleStartTime ist: "+showBubbleStartTime);
//		this.setScaleCenter(this.getWidth()/2, this.getHeight()/2);
//		this.setScale(0.00001f);
//		this.setVisible(true);
//		this.registerEntityModifier(new ScaleModifier(duration, 0.00001f, 1f)
//		{
//			protected void onModifierFinished(IEntity pItem)
//	        {
//				attachText();
//	        }
//		});
//		
//	}
//
//	/**
//	 * Closes the bubble with an animation. If detach == true, bubble
//	 * will detach itself from scene.
//	 * @param detach
//	 */
//	public void closeBubble(boolean detach)
//	{
//		closeBubble(0.25f, detach);
//	}
//	
//	/**
//	 * Closes the bubble with an animation. If detach == true, bubble
//	 * will detach itself from scene. Duration is how fast the bubble
//	 * will close itself. Use this if you are unsatisfied with the standard
//	 * duration that is applied when you use closeBubble(boolean detach)
//	 * @param duration
//	 * @param detach
//	 */
//	public void closeBubble(float duration, final boolean detach)
//	{
//		this.setScaleCenter(this.getWidth()/2, this.getHeight()/2);
//		this.registerEntityModifier(new ScaleModifier(duration, 1f, 0.001f)
//		{
//			protected void onModifierFinished(IEntity pItem)
//	        {
//				activity.runOnUpdateThread(new Runnable()
//				{
//					public void run()
//					{
//						if(detach)
//						{
//							speechBubbleText.detachSelf();
//							SpeechBubble.this.detachSelf();
//							
//						}
//						SpeechBubble.this.clearEntityModifiers(); 
//					}
//				});
//	        }
//	        
//		}
//		);
//	}
//	
//	public Ellipse createCircle() {
//		Ellipse cricleForBlockGuard = new Ellipse(getX(), getY(), 10, 10,
//				vertexBufferObjectManager);
//		cricleForBlockGuard.setColor(50f / 255f, 205f / 255f, 50f / 255f, 1f);
//		cricleForBlockGuard.setDrawMode(DrawMode.TRIANGLE_FAN);
//		// app.getSceneManager().getCurrentScene().attachChild(cricleForBlockGuard);
//		cricleForBlockGuard.setVisible(false);
//		return cricleForBlockGuard;
//	}

}
