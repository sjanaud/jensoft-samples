/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.horizontal;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect2;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>HBarGroupDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class HBarGroupDemo extends View {

	public HBarGroupDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		setBackground(Color.WHITE);

		Color blue = PetalPalette.PETAL1_HC;
		Color green = PetalPalette.PETAL2_HC;
		Color orange = PetalPalette.PETAL3_HC;

		// Group1
		BarSymbol b1g1 = new BarSymbol();
		b1g1.setDescentValue(62);
		b1g1.setThemeColor(blue);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		BarDefaultAxisLabel al = new BarDefaultAxisLabel("bar 1", Color.BLACK);
		al.setDrawColor(Color.BLACK);
		al.setFont(f);
		al.setFillColor(TangoPalette.SCARLETRED2);
		al.setTextColor(Color.WHITE);
		al.setText("Val1");
		al.setTextPaddingY(1);
		b1g1.setAxisLabel(al);

		BarSymbol b2g1 = new BarSymbol();
		b2g1.setDescentValue(83);
		b2g1.setThemeColor(green);

		BarSymbol b3g1 = new BarSymbol();
		b3g1.setDescentValue(47);
		b3g1.setThemeColor(orange);

		BarSymbolGroup group1 = new BarSymbolGroup();
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b2g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group1.addSymbol(b3g1);

		group1.setSymbol("Group 1");
		group1.setAxisLabel(new BarDefaultAxisLabel());
		group1.setName("group1");
		group1.setBase(-30);
		group1.setThickness(30);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw());
		group1.setBarFill(new BarFill1());
		group1.setBarEffect(new BarEffect2());

		b1g1.setName("b1");
		b1g1.setSymbol("bar 1");
		b2g1.setName("b2");
		b2g1.setSymbol("bar 2");
		b3g1.setName("b3");
		b3g1.setSymbol("bar 3");

		// Group2
		BarSymbol b1g2 = new BarSymbol();
		b1g2.setDescentValue(64);
		b1g2.setThemeColor(blue);

		BarSymbol b2g2 = new BarSymbol();
		b2g2.setDescentValue(77);
		b2g2.setThemeColor(green);

		BarSymbol b3g2 = new BarSymbol();
		b3g2.setDescentValue(32);
		b3g2.setThemeColor(orange);

		BarSymbolGroup group2 = new BarSymbolGroup();
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b2g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group2.addSymbol(b3g2);

		group2.setAxisLabel(new BarDefaultAxisLabel());
		group2.setSymbol("Group 2");
		group2.setName("group2");
		group2.setBase(-30);
		group2.setThickness(30);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw());
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect2());

		b1g2.setName("b3");
		b1g2.setSymbol("bar 3");
		b2g2.setName("b4");
		b2g2.setSymbol("bar 4");

		// Group3
		BarSymbol b1g3 = new BarSymbol();
		b1g3.setDescentValue(17);
		b1g3.setThemeColor(blue);

		BarSymbol b2g3 = new BarSymbol();
		b2g3.setDescentValue(43);
		b2g3.setThemeColor(green);

		BarSymbol b3g3 = new BarSymbol();
		b3g3.setDescentValue(78);
		b3g3.setThemeColor(orange);

		BarSymbolGroup group3 = new BarSymbolGroup();
		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b2g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 4));
		group3.addSymbol(b3g3);

		BarDefaultAxisLabel al2 = new BarDefaultAxisLabel("Group2", Color.BLACK);
		al2.setDrawColor(Color.BLACK);
		al2.setFont(f);
		al2.setFillColor(TangoPalette.SCARLETRED2);
		al2.setTextColor(Color.WHITE);
		al2.setText("G-2");
		al2.setTextPaddingX(5);
		al2.setTextPaddingY(1);
		al2.setOffsetX(-30);
		group3.setAxisLabel(al2);

		group3.setSymbol("Group 3");
		group3.setName("group3");
		group3.setBase(-30);
		group3.setThickness(30);
		group3.setRound(8);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(new BarDefaultDraw());
		group3.setBarFill(new BarFill1());
		group3.setBarEffect(new BarEffect2());

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
		proj.setName("h bar hroup proj");

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("H BarGroup Chart");
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
