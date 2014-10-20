package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class GenericTool<T> implements DrawTool {
	private String name = "UNDEFINED";
	/** 
	 * the image resource path. 
	 */
	private static final String IMAGES = "/images/";

	/**
	 * The context we use for drawing.
	 */
	private DrawContext context;

	/**
	 * The context's view. This variable can be used as a shortcut, i.e.
	 * instead of calling context.getView().
	 */
	private DrawView view;

	/**
	 * Temporary variable. During rectangle creation (during a
	 * mouse down - mouse drag - mouse up cycle) this variable refers
	 * to the new rectangle that is inserted.
	 */
	private Figure newFigure = null;

	/**
	 * Temporary variable.
	 * During rectangle creation this variable refers to the point the
	 * mouse was first pressed.
	 */
	private Point anchor = null;
	
	Class<T> shapeClass;
	
	public GenericTool(DrawContext context, Class<T> className) {
		this.context = context;
		this.view = context.getView();
		this.shapeClass = className;
		this.name = className.getSimpleName();
	}
	
	protected Figure createNewFigure(int x, int y){
		Figure f = null;
		try{
			f = (Figure) shapeClass.getConstructor(Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE).newInstance(x,y,0,0);
		}catch(Exception e){
			
		}
		return f;
	}
	
	@Override
	public void activate() {
		this.context.showStatusText(getName() + " Mode");
	}
	
	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if(newFigure != null){
			throw new IllegalStateException();
		}
		anchor = new Point(x,y);
		newFigure = createNewFigure(x, y);
		view.getModel().addFigure(newFigure);
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newFigure.setBounds(anchor, new Point(x,y));
		java.awt.Rectangle r = newFigure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newFigure = null;
		anchor = null;
		this.context.showStatusText( getName() +" Mode");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + getName().toLowerCase() + ".png"));
	}

	@Override
	public String getName() {
		return name;
	}

}
