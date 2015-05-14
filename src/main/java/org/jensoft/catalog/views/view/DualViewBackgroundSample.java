package org.jensoft.catalog.views.view;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.DualViewBackground;
import org.jensoft.core.view.background.ViewDarkBackground;


@JenSoftView(background=ViewDarkBackground.class,description="Show view dual texture/color background")
public class DualViewBackgroundSample extends View {


	private static final long serialVersionUID = 8448445190772014659L;

	/**
	* create view with the device outline
	*/
	public DualViewBackgroundSample() {
	
		
		// proj projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(NanoChromatique.PINK.brighter()));
		
		DualViewBackground bg = new DualViewBackground();
		bg.setTexture1(TexturePalette.getTriangleCarbonFiber());
		bg.setColor2(new Color(0,0,0,0));
		setBackgroundPainter(bg);

	}
	
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new DualViewBackgroundSample());
	}
	
}