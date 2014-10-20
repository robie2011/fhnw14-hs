package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class RectTool extends AbstractTool {

	public RectTool(DrawContext context) {
		super(context, "Rectangle");
	}

	@Override
	protected Figure createNewFigure(int x, int y) {
		return new Rect(x, y, 0, 0);
	}

}
