package org.jensoft.catalog.views.view;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.DebugPaintProjectionPartPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>ViewAnatomy</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show view component anatomy which is built with 5 parts: Device, West, East, South and West")
public class ViewAnatomy extends View {


	private static final long serialVersionUID = 8448445190772014659L;

	/**
	* create view with the device outline
	*/
	public ViewAnatomy() {
	
		//set yourself place holder according to your needs, metrics,legend, etc.
		setPlaceHolder(20, ViewPart.East);
		setPlaceHolder(80, ViewPart.West);
		setPlaceHolder(80, ViewPart.South);
		setPlaceHolder(20, ViewPart.North);

		// proj projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

		//show each part of view anatomy
		DebugPaintProjectionPartPlugin showParts = new DebugPaintProjectionPartPlugin();
		proj.registerPlugin(showParts);

		//create view general background
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));       
		setBackgroundPainter(viewBackground);
	
	}
	
}