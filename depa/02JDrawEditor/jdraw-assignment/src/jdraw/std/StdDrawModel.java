/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import jdraw.framework.DrawModelEvent.Type;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author RobertRajakone
 *
 */
public class StdDrawModel implements DrawModel {
	ArrayList<Figure> figures = new ArrayList<Figure>();
	ArrayList<DrawModelListener> drawModelListeners = new ArrayList<DrawModelListener>();
	Logger logger = Logger.getLogger(this.getClass());
	FigureListener figureChangeListener;

	public StdDrawModel(){
		logger.addAppender(new ConsoleAppender());
		logger.setLevel(Level.ALL);
		
		figureChangeListener = new FigureListener() {
			
			@Override
			public void figureChanged(FigureEvent e) {
				notifyDrawModelListeners(e.getFigure(), Type.FIGURE_CHANGED);
				
			}
		};
	}
	
	@Override
	public void addFigure(Figure f) {
		logger.trace("call addFigure()");
		if(figures.contains(f)) return;		
		
		figures.add(f);
		f.addFigureListener(figureChangeListener);		
		notifyDrawModelListeners(f, Type.FIGURE_ADDED);
	}

	
	
	@Override
	public Iterable<Figure> getFigures() {
		logger.trace("call getFigures()");
		return figures;
		//return new LinkedList<Figure>(figures);
	}

	@Override
	public void removeFigure(Figure f) {
		logger.trace("call removeFigure()");
		
		if(!figures.contains(f)) return;
		f.removeFigureListener(figureChangeListener);
		figures.remove(f);
		notifyDrawModelListeners(f, Type.FIGURE_REMOVED);
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		logger.trace("call addModelChangeListener()");
		drawModelListeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		logger.trace("call removeModelChangeListener()");
		drawModelListeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		logger.trace("call setFigureIndex");		
		if(!figures.contains(f)) throw new IllegalArgumentException();
		if(index < 0 || index >= figures.size()) throw new IndexOutOfBoundsException();
		
		figures.remove(f);
		figures.add(index, f);
		notifyDrawModelListeners(f, Type.DRAWING_CHANGED);
	}

	@Override
	// Important: Clone ArrayList to avoid ConcurrentModificationException
	public void removeAllFigures() {
		logger.trace("call removeAllFigures");

		ArrayList<Figure> localFigures = (ArrayList<Figure>) figures.clone();
		for (Figure f : localFigures)
			removeFigure(f);
		
		for (DrawModelListener drawModelListener : drawModelListeners) {
			drawModelListener.modelChanged(new DrawModelEvent(this, null, Type.DRAWING_CLEARED));
		}
		drawModelListeners = null;
	}
	
	void notifyDrawModelListeners(Figure f, Type t){
		for (DrawModelListener drawModelListener : drawModelListeners)
			drawModelListener.modelChanged(new DrawModelEvent(this, f, t));
	}

}
