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
import java.awt.Stroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.Stack;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.SymbolToolkit;
import org.jensoft.core.plugin.symbol.painter.draw.AbstractBarDraw;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill2;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.HorizontalAlignment;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.VerticalAlignment;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>HorizontalGantTypeDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HorizontalGantTypeDemo extends View {

	@Portfolio(name = "HorizontalGantTypeDemo", width = 500, height = 250)
	public static View getPortofolio() {
		HorizontalGantTypeDemo demo = new HorizontalGantTypeDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public HorizontalGantTypeDemo() {
		super(1);
		// view.setPartBackground(Color.WHITE, WindowPart.getAll());
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Color orange = Spectral.SPECTRAL_BLUE2;
		Color green = Spectral.SPECTRAL_PURPLE1;
		Color blue = Spectral.SPECTRAL_GREEN;
		Color pink = Spectral.SPECTRAL_YELLOW;
		Stroke s = new BasicStroke(0.7f);
		float[] fractions = { 0f, 0.5f, 1f };
		Color[] c = { new Color(0, 0, 0, 200), new Color(0, 0, 0, 255), new Color(0, 0, 0, 200) };
		Shader labelShader = new Shader(fractions, c);
		AbstractBarDraw barDraw = new BarDefaultDraw(Color.DARK_GRAY, s);
		// Font fontGroup = InputFonts.getElements(10);
		Font fontGroup = new Font("Dialog", Font.PLAIN, 10);
		// bar symbol 1 for group 1
		StackedBarSymbol b1g1 = new StackedBarSymbol();
		b1g1.setThemeColor(new Color(255, 255, 255));
		b1g1.setAscentValue(60);

		// bar symbol 1 label
		BarSymbolRelativeLabel b1g1RelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestRight);
		b1g1RelativeLabel.setText("Symbol 1");
		b1g1RelativeLabel.setTextColor(Color.WHITE);
		b1g1RelativeLabel.setFont(f);

		b1g1.setBarLabel(b1g1RelativeLabel);

		b1g1.addStack("orange", orange, 12);
		b1g1.addStack("green", green, 20);
		b1g1.addStack("blue", blue, 40);
		b1g1.addStack("pink", pink, 5);

		StackedBarSymbol b2g1 = new StackedBarSymbol();
		b2g1.setThemeColor(new Color(255, 255, 255));
		b2g1.setAscentValue(80);

		BarSymbolRelativeLabel b2g1RelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestRight);
		b2g1RelativeLabel.setText("Symbol 2");
		b2g1RelativeLabel.setTextColor(Color.WHITE);
		b2g1RelativeLabel.setFont(f);

		b2g1.setBarLabel(b2g1RelativeLabel);

		b2g1.addStack("orange", orange, 36);
		b2g1.addStack("green", green, 17);
		b2g1.addStack("blue", blue, 8);
		b2g1.addStack("pink", pink, 21);

		BarSymbolRelativeLabel groupRelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight);
		groupRelativeLabel.setText("Group 1");
		groupRelativeLabel.setDrawColor(Spectral.SPECTRAL_GREEN);
		groupRelativeLabel.setTextColor(Color.WHITE);
		groupRelativeLabel.setOutlineRound(10);
		groupRelativeLabel.setOutlineStroke(new BasicStroke(2f));
		// groupRelativeLabel.setOffsetX(20);
		// groupRelativeLabel.setTextPaddingX(6);
		groupRelativeLabel.setShader(labelShader);
		// b2g1.setBarLabel(rl3);
		groupRelativeLabel.setFont(fontGroup);

		BarSymbolGroup group1 = new BarSymbolGroup();

		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b2g1);

		group1.setSymbol("Group 1");
		group1.setName("group1");
		group1.setBase(-20);
		group1.setThickness(12);
		group1.setRound(6);
		group1.setMorpheStyle(MorpheStyle.Round);

		group1.setBarDraw(barDraw);
		group1.setBarFill(new BarFill2());
		// group1.setBarEffect(new BarEffect2());

		group1.setBarLabel(groupRelativeLabel);

		b1g1.setName("b1");
		b1g1.setSymbol("bar 1");
		b2g1.setName("b2");
		b2g1.setSymbol("bar 2");

		// Group2
		StackedBarSymbol b1g2 = new StackedBarSymbol();
		b1g2.setThemeColor(new Color(255, 255, 255));
		b1g2.setAscentValue(73);

		Stack s1b1g2 = SymbolToolkit.createStack("orange", orange, 12);
		Stack s2b1g2 = SymbolToolkit.createStack("green", green, 28);
		Stack s3b1g2 = SymbolToolkit.createStack("blue", blue, 13);
		Stack s4b1g2 = SymbolToolkit.createStack("pink", pink, 9);

		BarSymbolRelativeLabel s1b1g2Label = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.Middle);
		s1b1g2Label.setText("sk1");
		s1b1g2Label.setTextColor(Color.BLACK);
		s1b1g2Label.setFont(f);
		s1b1g2.setBarLabel(s1b1g2Label);

		BarSymbolRelativeLabel s2b1g2Label = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.Middle);
		s2b1g2Label.setText("sk2");
		s2b1g2Label.setTextColor(Color.BLACK);
		s2b1g2Label.setFont(f);

		s2b1g2.setBarLabel(s2b1g2Label);

		SymbolToolkit.pushStacks(b1g2, s1b1g2, s2b1g2, s3b1g2, s4b1g2);

		StackedBarSymbol b2g2 = new StackedBarSymbol();
		b2g2.setThemeColor(new Color(255, 255, 255));
		b2g2.setAscentValue(38);
		b2g2.addStack("orange", orange, 7);
		b2g2.addStack("green", green, 15);
		b2g2.addStack("blue", blue, 23);
		b2g2.addStack("pink", pink, 16);

		BarSymbolGroup group2 = new BarSymbolGroup();
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b2g2);

		BarSymbolRelativeLabel group2RelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft);
		group2RelativeLabel.setText("Group 2");
		group2RelativeLabel.setDrawColor(Spectral.SPECTRAL_BLUE1);
		group2RelativeLabel.setTextColor(Color.WHITE);
		group2RelativeLabel.setOutlineRound(10);
		group2RelativeLabel.setOutlineStroke(new BasicStroke(2));
		// groupRelativeLabel.setOffsetX(20);
		// groupRelativeLabel.setTextPaddingX(6);
		group2RelativeLabel.setShader(labelShader);
		// b2g1.setBarLabel(rl3);
		group2RelativeLabel.setFont(fontGroup);

		group2.setBarLabel(group2RelativeLabel);

		group2.setSymbol("Group 2");
		group2.setName("group2");
		group2.setBase(30);
		group2.setThickness(12);
		group2.setRound(6);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(barDraw);
		group2.setBarFill(new BarFill2());
		// group2.setBarEffect(new BarEffect1());

		b1g2.setName("b3");
		b1g2.setSymbol("bar 3");
		b2g2.setName("b4");
		b2g2.setSymbol("bar 4");

		// Group3
		StackedBarSymbol b1g3 = new StackedBarSymbol();
		b1g3.setThemeColor(new Color(255, 255, 255));
		b1g3.setAscentValue(53);
		b1g3.addStack("orange", orange, 39);
		b1g3.addStack("green", green, 6);
		b1g3.addStack("blue", blue, 17);
		b1g3.addStack("pink", pink, 10);

		StackedBarSymbol b2g3 = new StackedBarSymbol();
		b2g3.setThemeColor(new Color(255, 255, 255));
		b2g3.setAscentValue(22);
		b2g3.addStack("orange", orange, 6);
		b2g3.addStack("green", green, 45);
		b2g3.addStack("blue", blue, 7);
		b2g3.addStack("pink", pink, 13);

		BarSymbolGroup group3 = new BarSymbolGroup();
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b2g3);

		group3.setSymbol("Group 3");
		group3.setName("group 3");
		group3.setBase(0);
		group3.setThickness(12);
		group3.setRound(6);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(barDraw);
		group3.setBarFill(new BarFill2());
		// group3.setBarEffect(new BarEffect1());

		BarSymbolRelativeLabel group3RelativeLabel = new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight);
		group3RelativeLabel.setText("Group 2");
		group3RelativeLabel.setDrawColor(Spectral.SPECTRAL_PURPLE2);
		group3RelativeLabel.setTextColor(Color.WHITE);
		group3RelativeLabel.setOutlineRound(10);
		group3RelativeLabel.setOutlineStroke(new BasicStroke(2));
		// groupRelativeLabel.setOffsetX(20);
		// groupRelativeLabel.setTextPaddingX(6);
		group3RelativeLabel.setShader(labelShader);
		group3RelativeLabel.setFont(fontGroup);
		// b2g1.setBarLabel(rl3);

		group3.setBarLabel(group3RelativeLabel);

		b1g3.setName("b4");
		b1g3.setSymbol("bar 4");
		b2g3.setName("b5");
		b2g3.setSymbol("bar 5");

		// Window Projection
		Projection proj = new Projection.Linear(-30, 120, 0, 0);
		registerProjection(proj);

		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Horizontal);
		proj.registerPlugin(barPlugin);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 20, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		BarSymbolLayer barLayer = new BarSymbolLayer();

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 10));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		barPlugin.addLayer(barLayer);

	}

}
