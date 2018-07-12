package com.andgdx.text;

import com.andgdx.entity.IEntity;
import com.andgdx.entity.EntityCore;
import com.andgdx.texture.TextureOptions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.utils.Align;

public class Text extends EntityCore implements IEntity {
	private BitmapFont font;
	String text;
	int x, y;
	float width, height;

	
//	private static HAlignment currentAlign = BitmapFont.HAlignment.LEFT;
//	public static HAlignment LEFT = BitmapFont.HAlignment.LEFT;
//	public static HAlignment CENTER = BitmapFont.HAlignment.CENTER;
//	public static HAlignment RIGHT = BitmapFont.HAlignment.RIGHT;

	private static int currentAlign = Align.left;
	public static int LEFT = Align.left;
	public static int CENTER = Align.center;
	public static int RIGHT = Align.right;

	GlyphLayout layout = new GlyphLayout();
	
	public Text(int x, int y, String text) {
		this(x, y, text, Color.BLACK, getCurrentAlignment());
	}
	
	public Text(int x, int y, BitmapFont font, String text) {
		this(x, y,font, text, Color.BLACK, getCurrentAlignment(),null);
	}
	
	public Text(int x, int y, String text, Color color) {
		this(x, y, text, color, getCurrentAlignment());
	}
	
	public Text(int x, int y, BitmapFont font, String text, Color color) {
		this(x, y, font, text, color, getCurrentAlignment(),null);
	}

//	public Text(int x, int y, String text, Color color, HAlignment align) {
//		this(x, y, new BitmapFont(), text, color, align,null);
//	}

	public Text(int x, int y, String text, Color color, int align) {
		this(x, y, new BitmapFont(), text, color, align,null);
	}
	
	
	public Text(int x, int y, BitmapFont font, String text, Color color, int align, TextureOptions options) {
		if(font == null)
		{
			font = new BitmapFont();
		}
		this.font = font;
		font.setColor(color);
		this.text = text;
		this.x = x;
		this.y = y;
		setX(x);
		setY(y);
//		this.width = font.getBounds(text).width;
//		this.height = font.getBounds(text).height;
		layout.setText(font,text);
		this.width = layout.width;
		this.height = layout.height;
		
		currentAlign = align;
		
		if(options != null)
		{
			applyFilterAndWrap(font.getRegion().getTexture(), options);
		}
		else
		{
			applyFilterAndWrap(font.getRegion().getTexture(), TextureOptions.BILINEAR);
		}
	}

	
//	public Text(int x, int y, BitmapFont font, String text, Color color, HAlignment align, TextureOptions options) {
//		if(font == null)
//		{
//			font = new BitmapFont();
//		}
//		this.font = font;
//		font.setColor(color);
//		this.text = text;
//		this.x = x;
//		this.y = y;
//		setX(x);
//		setY(y);
//		this.width = font.getBounds(text).width;
//		this.height = font.getBounds(text).height;
//		
//		currentAlign = align;
//		
//		if(options != null)
//		{
//			applyFilterAndWrap(font.getRegion().getTexture(), options);
//		}
//		else
//		{
//			applyFilterAndWrap(font.getRegion().getTexture(), TextureOptions.BILINEAR);
//		}
//	}
	
	
	protected void applyFilterAndWrap(Texture texture, TextureOptions options) {
		texture.setFilter(options.mMinFilter, options.mMagFilter);  
		texture.setWrap(options.mWrapT, options.mWrapS);
	}

//	public void setText(String newText) {
//		text = newText;
//		this.width = font.getBounds(text).width;
//		this.height = font.getBounds(text).height;
//	}
	public void setText(String newText) {
		text = newText;
		layout.setText(getFont(),getText());
		this.width = layout.width;
		this.height = layout.height;
	}
	
	
//	public float getX() {
//		return x;
//	}
//
//	public float getY() {
//		return y;
//	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}
	
	
	public void setX(float x) {
		super.setX(x);
		this.x = (int)x;
	}

	public void setY(float y) {
		super.setY(y);
		this.y = (int)y;
	}

	public void getHeight(float height) {
		this.height = (int)height;
	}

	public void getWidth(float width) {
		this.width = width;
	}

	@Override
	public void draw(Batch batch, float alpha) {

		// font.drawWrapped(batch, text, x, y, width, currentAlign);
//		font.drawMultiLine(batch, text, getX(), getY(), getWidth() + 20, currentAlign);
		
		
		layout.setText(getFont(),getText());
		getFont().draw(batch, getText(), getX(), getY(), getWidth() + 20, getCurrentAlignment(), true);

		super.draw(batch, alpha);

	}

	public static int getCurrentAlignment() {
		return currentAlign;
	}

	public String getText() {
		return text;
	}

	public BitmapFont getFont() {
		return font;
	}
	
	
	
}
