package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class OvalTool extends AbstractTool {

	public OvalTool(DrawContext context) {
		super(context, "oval");
	}

	@Override
	protected Figure createNewFigure(int x, int y) {
		return new Oval(x, y, 0, 0);
	}

}
