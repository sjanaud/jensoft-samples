/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut2D.other;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D2DMultiUserProjection</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to create multiple donut2D in the user projection coordinate system.")
public class D2DMultiUserProjection extends View {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a donut2D demo with 3 Donut2D which are projected in the user
	 * projection
	 */
	public D2DMultiUserProjection() {
		super();

		// projection
		Projection proj = new Projection.Linear(-2, 2, -2, 2);
		proj.setName("donuts2D");
		proj.registerPlugin(new OutlinePlugin(RosePalette.MELON));

		// zoom wheel plug-in
		proj.registerPlugin(new ZoomWheelPlugin());

		// translate plug-in
		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		// donut2D plug-in
		Donut2DPlugin donut2D = new Donut2DPlugin();
		proj.registerPlugin(donut2D);

		// donut2D 1
		Donut2D donut1 = new Donut2D();
		donut1.setNature(Donut2DNature.User);
		donut1.setCenterX(-1);
		donut1.setCenterY(0);
		donut1.setInnerRadius(30);
		donut1.setOuterRadius(50);
		donut1.setStartAngleDegree(50);

		// slice for donut 1
		final Donut2DSlice s1 = new Donut2DSlice("s1", new Color(139, 196, 40));
		s1.setAlpha(0.5f);
		s1.setValue(20.0);

		final Donut2DSlice s2 = new Donut2DSlice("s2", new Color(213, 222, 35));
		s2.setValue(20.0);
		s2.setAlpha(0.5f);

		final Donut2DSlice s3 = new Donut2DSlice("s3", new Color(78, 148, 44));
		s3.setValue(20.0);
		s3.setAlpha(0.5f);

		donut1.addSlice(s1);
		donut1.addSlice(s2);
		donut1.addSlice(s3);

		donut2D.addDonut(donut1);

		// donut2D 2
		Donut2D donut2 = new Donut2D();
		donut2.setNature(Donut2DNature.User);
		donut2.setCenterX(0);
		donut2.setCenterY(0);
		donut2.setInnerRadius(30);
		donut2.setOuterRadius(50);
		donut2.setStartAngleDegree(120);

		// slices for donut2D 2
		final Donut2DSlice s4 = new Donut2DSlice("s4", new Color(0, 176, 217));
		s4.setValue(30.0);
		s4.setAlpha(0.4f);
		final Donut2DSlice s5 = new Donut2DSlice("s5", new Color(174, 221, 227));
		s5.setValue(10.0);
		s5.setAlpha(0.7f);
		final Donut2DSlice s6 = new Donut2DSlice("s6", new Color(28, 152, 183));
		s6.setValue(20.0);
		s6.setAlpha(0.7f);

		donut2.addSlice(s4);
		donut2.addSlice(s5);
		donut2.addSlice(s6);

		donut2D.addDonut(donut2);

		// donut2D 3
		Donut2D donut3 = new Donut2D();
		donut3.setNature(Donut2DNature.User);
		donut3.setCenterX(1);
		donut3.setCenterY(0);
		donut3.setInnerRadius(30);
		donut3.setOuterRadius(50);
		donut3.setStartAngleDegree(120);

		// slices for donut2D 3
		final Donut2DSlice s7 = new Donut2DSlice("S7", new Color(240, 90, 40));
		s7.setValue(40.0);
		s7.setAlpha(0.7f);
		final Donut2DSlice s8 = new Donut2DSlice("s8", new Color(251, 148, 9));
		s8.setValue(25);
		s8.setAlpha(0.7f);

		final Donut2DSlice s9 = new Donut2DSlice("s9", new Color(251, 174, 66));
		s9.setValue(25);
		s9.setAlpha(0.5f);

		donut3.addSlice(s7);
		donut3.addSlice(s8);
		donut3.addSlice(s9);

		donut2D.addDonut(donut3);

		registerProjection(proj);

		Thread donut1Thread = new Thread(new Donut1Animator(s1, s2, s3), "donut1Thread");
		donut1Thread.start();

		Thread donut2Thread = new Thread(new Donut2Animator(s4, s5, s6), "donut2Thread");
		donut2Thread.start();

		Thread donut3Thread = new Thread(new Donut3Animator(s7, s8, s9), "donut3Thread");
		donut3Thread.start();

	}

	/**
	 * Donut1 animator
	 */
	private class Donut1Animator implements Runnable {

		/** donut2D slices */
		private Donut2DSlice s1;
		private Donut2DSlice s2;
		private Donut2DSlice s3;

		/**
		 * create Donut1 runnable animator
		 * 
		 * @param s1
		 * @param s2
		 * @param s3
		 */
		public Donut1Animator(Donut2DSlice s1, Donut2DSlice s2, Donut2DSlice s3) {
			this.s1 = s1;
			this.s2 = s2;
			this.s3 = s3;
		}

		@Override
		public void run() {
			try {

				Thread.sleep(1000);

				int tempo = 300;
				while (true) {
					s1.setValue(40);
					s2.setValue(40);
					s3.setValue(40);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(30);
					s2.setValue(45);
					s3.setValue(45);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(20);
					s2.setValue(50);
					s3.setValue(50);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(10);
					s2.setValue(55);
					s3.setValue(55);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(11);
					s2.setValue(50);
					s3.setValue(60);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(12);
					s2.setValue(45);
					s3.setValue(65);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(13);
					s2.setValue(50);
					s3.setValue(60);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(10);
					s2.setValue(55);
					s3.setValue(55);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(20);
					s2.setValue(50);
					s3.setValue(50);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(30);
					s2.setValue(45);
					s3.setValue(45);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s1.setValue(40);
					s2.setValue(40);
					s3.setValue(40);
					s1.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * Donut2 animator
	 */
	private class Donut2Animator implements Runnable {

		private Donut2DSlice s4;
		private Donut2DSlice s5;
		private Donut2DSlice s6;

		/**
		 * create Donut2 runnable animator
		 * 
		 * @param s4
		 * @param s5
		 * @param s6
		 */
		public Donut2Animator(Donut2DSlice s4, Donut2DSlice s5, Donut2DSlice s6) {
			super();
			this.s4 = s4;
			this.s5 = s5;
			this.s6 = s6;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {

				Thread.sleep(1000);
				int tempo = 100;
				while (true) {
					s4.setValue(40);
					s5.setValue(40);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(30);
					s5.setValue(45);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(20);
					s5.setValue(50);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(10);
					s5.setValue(55);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(11);
					s5.setValue(50);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(12);
					s5.setValue(45);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(13);
					s5.setValue(50);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(10);
					s5.setValue(55);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(20);
					s5.setValue(50);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(30);
					s5.setValue(45);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

					s4.setValue(40);
					s5.setValue(40);

					s4.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * Donut3 animator
	 */
	private class Donut3Animator implements Runnable {

		private Random rand = new Random();

		private Donut2DSlice s7;
		private Donut2DSlice s8;
		private Donut2DSlice s9;

		/**
		 * create Donut3 animator
		 * 
		 * @param s7
		 * @param s8
		 * @param s9
		 */
		public Donut3Animator(Donut2DSlice s7, Donut2DSlice s8, Donut2DSlice s9) {
			super();
			this.s7 = s7;
			this.s8 = s8;
			this.s9 = s9;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {

				int tempo = 300;
				while (true) {

					s7.setValue(rand.nextInt(100));
					s8.setValue(25);

					s7.getHost().getHostPlugin().repaintDevice();
					Thread.sleep(tempo);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * create image view portfolio for
	 * {@link #Donut2DMultipleUserProjectionDemo()}
	 * 
	 * @return portfolio view
	 */
	@Portfolio(name = "Donut2DMultipleUserProjectionDemo", width = 500, height = 250)
	public static View getPortofolio() {
		D2DMultiUserProjection demo = new D2DMultiUserProjection();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
