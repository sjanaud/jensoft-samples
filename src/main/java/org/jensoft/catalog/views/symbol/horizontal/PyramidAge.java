/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.horizontal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.format.IMetricsFormat;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarEvent;
import org.jensoft.core.plugin.symbol.BarListener;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.PointSymbol;
import org.jensoft.core.plugin.symbol.PointSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.HorizontalAlignment;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.VerticalAlignment;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolDebugGeometryPainter;
import org.jensoft.core.plugin.symbol.painter.point.PointSymbolImage;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.sharedicon.SharedIcon;
import org.jensoft.core.sharedicon.marker.Marker;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>PyramidAge</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class PyramidAge extends View {

	@Portfolio(name = "Doc Portfolio - France Demography", width = 600, height = 320)
	public static View getPortofolio() {
		PyramidAge demo = new PyramidAge();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public PyramidAge() {

		super();
		Projection proj = new Projection.Linear(-700000, 700000, 0, 0);
		registerProjection(proj);

		proj.registerPlugin(new OutlinePlugin());
		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(barPlugin);

		BarSymbolLayer menLayer = new BarSymbolLayer();
		BarSymbolLayer womenLayer = new BarSymbolLayer();
		menLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		womenLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		Collections.reverse(years);

		PointSymbolLayer pointSymbolLayer = new PointSymbolLayer();
		pointSymbolLayer.addSymbol(SymbolComponent.createGlue(PointSymbol.class));

		for (YearDemography yd : years) {

			if (yd.year == 1976) {
				PointSymbol ps = new PointSymbol(0);
				Image i = SharedIcon.getMarker(Marker.MARKER).getImage();
				PointSymbolImage img = new PointSymbolImage(i);
				img.setOffsetX(-i.getWidth(null) / 2);
				img.setOffsetY(-i.getHeight(null));

				ps.addPointSymbolPainter(img);
				ps.addPointSymbolPainter(new PointSymbolDebugGeometryPainter(Color.YELLOW));
				pointSymbolLayer.addSymbol(ps);
			}

			pointSymbolLayer.addSymbol(SymbolComponent.createStrut(PointSymbol.class, 2));

		}

		for (YearDemography yd : years) {

			System.out.println(yd);
			BarSymbol men = new BarSymbol("men " + yd.year);
			BarSymbol women = new BarSymbol("women " + yd.year);

			men.setUserObject(yd);
			women.setUserObject(yd);

			men.setBase(0);
			women.setBase(0);

			men.setThickness(2);
			women.setThickness(2);

			men.setAscentValue(yd.men);
			women.setDescentValue(yd.women);

			men.setThemeColor(FilPalette.BLUE12);
			women.setThemeColor(FilPalette.PINK1);

			men.setBarFill(new BarFill1());
			women.setBarFill(new BarFill1());

			// men.setBarEffect(new BarEffect2());
			// women.setBarEffect(new BarEffect2());

			menLayer.addSymbol(men);
			// menLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class,
			// 1));

			womenLayer.addSymbol(women);
			// womenLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class,
			// 1));

		}

		menLayer.addBarListener(new BarListener() {

			@Override
			public void barSymbolReleased(BarEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void barSymbolPressed(BarEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void barSymbolExited(BarEvent e) {
				// TODO Auto-generated method stub
				e.getBarSymbol().setBarLabel(null);
				e.getBarSymbol().getHost().getProjection().getView().repaintDevice();
			}

			@Override
			public void barSymbolEntered(BarEvent e) {
				Font f =  new Font("Dialog", Font.PLAIN, 12);
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] c = { new Color(0, 0, 0, 200), new Color(0, 0, 0, 255), new Color(0, 0, 0, 200) };
				Shader labelShader = new Shader(fractions, c);
				Font fontGroup = new Font("Dialog", Font.PLAIN, 10);;
				BarSymbolRelativeLabel groupRelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight);

				groupRelativeLabel.setDrawColor(FilPalette.BLUE12);
				groupRelativeLabel.setTextColor(Color.WHITE);
				groupRelativeLabel.setOutlineRound(10);
				groupRelativeLabel.setOutlineStroke(new BasicStroke(2f));
				// groupRelativeLabel.setOffsetX(20);
				// groupRelativeLabel.setTextPaddingX(6);
				groupRelativeLabel.setShader(labelShader);
				// b2g1.setBarLabel(rl3);
				groupRelativeLabel.setFont(fontGroup);

				BarSymbol bs = e.getBarSymbol();
				YearDemography yd = (YearDemography) bs.getUserObject();
				groupRelativeLabel.setText(yd.year + " : " + yd.men + " men");
				bs.setBarLabel(groupRelativeLabel);
				e.getBarSymbol().getHost().getProjection().getView().repaintDevice();
			}

			@Override
			public void barSymbolClicked(BarEvent e) {

			}
		});

		womenLayer.addBarListener(new BarListener() {

			@Override
			public void barSymbolReleased(BarEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void barSymbolPressed(BarEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void barSymbolExited(BarEvent e) {
				// TODO Auto-generated method stub
				e.getBarSymbol().setBarLabel(null);
				e.getBarSymbol().getHost().getProjection().getView().repaintDevice();
			}

			@Override
			public void barSymbolEntered(BarEvent e) {
				Font f =  new Font("Dialog", Font.PLAIN, 12);
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] c = { new Color(0, 0, 0, 200), new Color(0, 0, 0, 255), new Color(0, 0, 0, 200) };
				Shader labelShader = new Shader(fractions, c);
				Font fontGroup =new Font("Dialog", Font.PLAIN, 10);
				BarSymbolRelativeLabel groupRelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft);

				groupRelativeLabel.setDrawColor(FilPalette.PINK1);
				groupRelativeLabel.setTextColor(Color.WHITE);
				groupRelativeLabel.setOutlineRound(10);
				groupRelativeLabel.setOutlineStroke(new BasicStroke(2f));
				// groupRelativeLabel.setOffsetX(20);
				// groupRelativeLabel.setTextPaddingX(6);
				groupRelativeLabel.setShader(labelShader);
				// b2g1.setBarLabel(rl3);
				groupRelativeLabel.setFont(fontGroup);

				BarSymbol bs = e.getBarSymbol();
				YearDemography yd = (YearDemography) bs.getUserObject();
				groupRelativeLabel.setText(yd.year + " : " + yd.women + " women");
				bs.setBarLabel(groupRelativeLabel);
				e.getBarSymbol().getHost().getProjection().getView().repaintDevice();
			}

			@Override
			public void barSymbolClicked(BarEvent e) {

			}
		});

		barPlugin.addLayer(menLayer);
		barPlugin.addLayer(womenLayer);
		barPlugin.addLayer(pointSymbolLayer);

		StripePlugin bandLayout = new StripePlugin.MultiplierStripe.V(0, 100000);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		bandLayout.setStripePalette(bp);
		bandLayout.setAlpha(0.3f);
		proj.registerPlugin(bandLayout);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 100000, GridOrientation.Vertical);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		// a free grid for base line

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		Font f = new Font("Dialog", Font.PLAIN, 12);
		southMetrics.setTextFont(f);
		

		southMetrics.getMetricsManager().setMetricsFormat(new IMetricsFormat() {

			@Override
			public String format(double d) {
				return "" + Math.abs(d);
			}
		});

		// LEGEND
		TitleLegendPlugin lgendL = new TitleLegendPlugin();

		TitleLegend legend = new TitleLegend("France Demography");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.COPPER2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));

		TitleLegend menLegend = new TitleLegend("Men");
		menLegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.BLUE2));
		menLegend.setFont(f);
		menLegend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));

		TitleLegend womenLegend = new TitleLegend("Women");
		womenLegend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.PINK1));
		womenLegend.setFont(f);
		womenLegend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Left));

		lgendL.addLegend(legend);
		lgendL.addLegend(menLegend);
		lgendL.addLegend(womenLegend);

		proj.registerPlugin(lgendL);

		TranslatePlugin tp = new TranslatePlugin();
		tp.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(tp);

	}

	static List<YearDemography> years = new ArrayList<YearDemography>();

	static class YearDemography {

		int year;
		int ellapsedAge;
		int total;
		int men;
		int women;

		public YearDemography(int year, int ellapsedAge, int total, int men, int women) {
			super();
			this.year = year;
			this.ellapsedAge = ellapsedAge;
			this.total = total;
			this.men = men;
			this.women = women;

			years.add(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "YearDemography [year=" + year + ", ellapsedAge=" + ellapsedAge + ", total=" + total + ", men=" + men + ", women=" + women + "]";
		}

	}

	static {

		YearDemography yd;
		yd = new YearDemography(2011, 0, 822621, 420230, 402391);
		yd = new YearDemography(2010, 1, 825536, 421749, 403787);
		yd = new YearDemography(2009, 2, 816582, 417304, 399278);
		yd = new YearDemography(2008, 3, 795482, 406612, 388870);
		yd = new YearDemography(2007, 4, 794462, 405892, 388570);
		yd = new YearDemography(2006, 5, 809828, 414173, 395655);
		yd = new YearDemography(2005, 6, 796668, 406870, 389798);
		yd = new YearDemography(2004, 7, 793712, 406121, 387591);
		yd = new YearDemography(2003, 8, 794984, 405806, 389178);
		yd = new YearDemography(2002, 9, 804327, 412429, 391898);
		yd = new YearDemography(2001, 10, 818285, 418138, 400147);
		yd = new YearDemography(2000, 11, 835515, 428514, 407001);
		yd = new YearDemography(1999, 12, 804662, 412252, 392410);
		yd = new YearDemography(1998, 13, 798449, 409328, 389121);
		yd = new YearDemography(1997, 14, 787265, 404275, 382990);
		yd = new YearDemography(1996, 15, 795740, 407808, 387932);
		yd = new YearDemography(1995, 16, 791414, 404471, 386943);
		yd = new YearDemography(1994, 17, 771655, 393846, 377809);
		yd = new YearDemography(1993, 18, 769043, 392453, 376590);
		yd = new YearDemography(1992, 19, 809037, 412671, 396366);
		yd = new YearDemography(1991, 20, 818155, 414980, 403175);
		yd = new YearDemography(1990, 21, 824026, 418549, 405477);
		yd = new YearDemography(1989, 22, 822136, 414838, 407298);
		yd = new YearDemography(1988, 23, 820659, 414644, 406015);
		yd = new YearDemography(1987, 24, 806806, 404859, 401947);
		yd = new YearDemography(1986, 25, 808404, 403116, 405288);
		yd = new YearDemography(1985, 26, 798896, 396981, 401915);
		yd = new YearDemography(1984, 27, 784393, 388019, 396374);
		yd = new YearDemography(1983, 28, 771630, 382134, 389496);
		yd = new YearDemography(1982, 29, 822730, 406443, 416287);
		yd = new YearDemography(1981, 30, 833230, 410364, 422866);
		yd = new YearDemography(1980, 31, 845114, 416157, 428957);
		yd = new YearDemography(1979, 32, 801192, 395250, 405942);
		yd = new YearDemography(1978, 33, 784103, 387876, 396227);
		yd = new YearDemography(1977, 34, 796288, 394050, 402238);
		yd = new YearDemography(1976, 35, 777116, 384957, 392159);
		yd = new YearDemography(1975, 36, 802821, 397647, 405174);
		yd = new YearDemography(1974, 37, 850588, 420914, 429674);

		yd = new YearDemography(1973, 38, 899948, 445510, 454438);
		yd = new YearDemography(1972, 39, 926479, 459200, 467279);
		yd = new YearDemography(1971, 40, 925679, 458803, 466876);
		yd = new YearDemography(1970, 41, 908290, 449601, 458689);
		yd = new YearDemography(1969, 42, 895659, 442687, 452972);
		yd = new YearDemography(1968, 43, 891852, 440105, 451747);
		yd = new YearDemography(1967, 44, 889115, 439398, 449717);
		yd = new YearDemography(1966, 45, 910269, 448357, 461912);
		yd = new YearDemography(1965, 46, 914040, 450784, 463256);
		yd = new YearDemography(1964, 47, 923937, 454069, 469868);
		yd = new YearDemography(1963, 48, 912926, 447673, 465253);

		yd = new YearDemography(1973, 38, 899948, 445510, 454438);
		yd = new YearDemography(1972, 39, 926479, 459200, 467279);
		yd = new YearDemography(1971, 40, 925679, 458803, 466876);
		yd = new YearDemography(1970, 41, 908290, 449601, 458689);
		yd = new YearDemography(1969, 42, 895659, 442687, 452972);
		yd = new YearDemography(1968, 43, 891852, 440105, 451747);
		yd = new YearDemography(1967, 44, 889115, 439398, 449717);
		yd = new YearDemography(1966, 45, 910269, 448357, 461912);
		yd = new YearDemography(1965, 46, 914040, 450784, 463256);
		yd = new YearDemography(1964, 47, 923937, 454069, 469868);
		yd = new YearDemography(1963, 48, 912926, 447673, 465253);
		yd = new YearDemography(1962, 49, 883679, 432883, 450796);
		yd = new YearDemography(1961, 50, 884664, 433513, 451151);
		yd = new YearDemography(1960, 51, 879074, 430584, 448490);
		yd = new YearDemography(1959, 52, 874272, 427256, 447016);
		yd = new YearDemography(1958, 53, 854033, 414679, 439354);
		yd = new YearDemography(1957, 54, 851946, 414604, 437342);
		yd = new YearDemography(1956, 55, 846074, 409852, 436222);
		yd = new YearDemography(1955, 56, 839076, 405479, 433597);
		yd = new YearDemography(1954, 57, 837979, 406026, 431953);
		yd = new YearDemography(1953, 58, 821197, 396905, 424292);
		yd = new YearDemography(1952, 59, 835417, 403582, 431835);
		yd = new YearDemography(1951, 60, 815161, 394806, 420355);
		yd = new YearDemography(1950, 61, 848175, 410640, 437535);
		yd = new YearDemography(1949, 62, 832451, 402234, 430217);
		yd = new YearDemography(1948, 63, 829557, 402052, 427505);
		yd = new YearDemography(1947, 64, 809323, 391012, 418311);
		yd = new YearDemography(1946, 65, 766145, 368862, 397283);
		yd = new YearDemography(1945, 66, 579210, 276948, 302262);
		yd = new YearDemography(1944, 67, 566870, 269167, 297703);
		yd = new YearDemography(1943, 68, 548643, 260639, 288004);
		yd = new YearDemography(1942, 69, 510759, 241404, 269355);
		yd = new YearDemography(1941, 70, 452659, 211514, 241145);
		yd = new YearDemography(1940, 71, 471554, 217922, 253632);
		yd = new YearDemography(1939, 72, 492778, 224378, 268400);
		yd = new YearDemography(1938, 73, 481422, 215877, 265545);
		yd = new YearDemography(1937, 74, 469642, 208991, 260651);
		yd = new YearDemography(1936, 75, 470077, 205741, 264336);
		yd = new YearDemography(1935, 76, 455631, 195353, 260278);
		yd = new YearDemography(1934, 77, 457015, 193672, 263343);
		yd = new YearDemography(1933, 78, 434174, 179483, 254691);
		yd = new YearDemography(1932, 79, 438426, 178223, 260203);
		yd = new YearDemography(1931, 80, 414906, 164605, 250301);
		yd = new YearDemography(1930, 81, 405765, 157853, 247912);
		yd = new YearDemography(1929, 82, 364739, 138075, 226664);
		yd = new YearDemography(1928, 83, 343668, 126198, 217470);
		yd = new YearDemography(1927, 84, 316432, 112646, 203786);
		yd = new YearDemography(1926, 85, 293242, 101038, 192204);
		yd = new YearDemography(1925, 86, 268313, 89419, 178894);
		yd = new YearDemography(1924, 87, 236683, 75381, 161302);
		yd = new YearDemography(1923, 88, 210595, 64199, 146396);
		yd = new YearDemography(1922, 89, 186055, 54897, 131158);
		yd = new YearDemography(1921, 90, 163492, 46464, 117028);
		yd = new YearDemography(1920, 91, 138392, 37857, 100535);
		yd = new YearDemography(1919, 92, 68000, 17105, 50895);
		yd = new YearDemography(1918, 93, 48704, 11237, 37467);
		yd = new YearDemography(1917, 94, 34609, 7268, 27341);
		yd = new YearDemography(1916, 95, 24546, 4802, 19744);
		yd = new YearDemography(1915, 96, 22273, 4096, 18177);
		yd = new YearDemography(1914, 97, 25438, 4543, 20895);
		yd = new YearDemography(1913, 98, 16972, 2476, 14496);
		yd = new YearDemography(1912, 99, 10962, 1341, 9621);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final ViewFrameUI demoFrame = new ViewFrameUI(new PyramidAge());
	}

}
