package com.andgdx.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;

public class TickerText extends Text {

	int defaultCharsPerSecond = 3;
	Typewriter writer = new Typewriter(true,true);
	String tempText;
	
	
	
	public TickerText(int x, int y, BitmapFont font, String text, float charsPerSecond) {
		super(x, y, font, text);
		init(charsPerSecond);
	}
	
	
	
	
	public TickerText(int x, int y, String text, float charsPerSecond) {
		super(x, y, text);
		init(charsPerSecond);
	}




	
	
	public TickerText(int x, int y, String text, float charsPerSecond, Color color) {
		super(x, y, text, color);
		init(charsPerSecond);
	}
	
	public TickerText(int x, int y, BitmapFont font, String tickerText,float charsPerSecond, Color color) {
		super( x, y, font,tickerText,  color);
		init(charsPerSecond);
	}

 

	public TickerText(int x, int y, String text,  float charsPerSecond,Color color, int align) {
		super(  x,   y,   text,  color,   align);
		init(charsPerSecond);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public TickerText(int x, int y, BitmapFont font, String text) {
		super(x, y, font, text);
		init(0);
	}
	
	
	
	
	public TickerText(int x, int y, String text) {
		super(x, y, text);
		init(0);
	}




	
	
	public TickerText(int x, int y, String text, Color color) {
		super(x, y, text, color);
		init(0);
	}
	
	public TickerText(int x, int y, BitmapFont font, String tickerText, Color color) {
		super( x, y, font,tickerText,  color);
		init(0);
	}

 

	public TickerText(int x, int y, String text, Color color, int align) {
		super(  x,   y,   text,  color,   align);
		init(0);
	}
	
	
	public void init(float charsPerSecond) {
		float speed = (charsPerSecond  == 0 ) ? defaultCharsPerSecond : charsPerSecond;
		writer.setCharsPerSecond(speed);
		writer.getInterpolator().setInterpolation(Interpolation.linear);
	}
	
	public void setInterpolator(Interpolation interpol)
	{
		writer.getInterpolator().setInterpolation(interpol);
	}
	
	public void setCharsPerSecond(float charsPerSecond)
	{
		writer.setCharsPerSecond(charsPerSecond);
	}
	

	@Override
	public void draw(Batch batch, float alpha) {
		tempText = writer.updateAndType(getText(),Gdx.graphics.getDeltaTime()).toString();
		layout.setText(getFont(),tempText);
		getFont().draw(batch, tempText, getX(), getY(), getWidth() + 20, getCurrentAlignment(), true);
	}
	
}
