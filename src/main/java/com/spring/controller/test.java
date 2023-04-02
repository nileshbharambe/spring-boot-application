package com.spring.controller;

import java.awt.Color;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class test {

	public static void main(String[] args) {

		File filePath = new File("C:/Temp/");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		// initialize variables
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		
		String infile, infileDate;
		double ratioCategoryA = 0;
		double ratioCategoryB = 0;
		double ratioCategoryC = 0;

		// initialize variables
		String strLine;
		int countCategoryA = 10;
		int countCategoryB = 40;
		int countCategoryC = 50;

		// calculate ratio for each event type
		int sum = countCategoryA + countCategoryB + countCategoryC;
		ratioCategoryA = (countCategoryA / (double) sum) * 100.0;
		ratioCategoryB = (countCategoryB / (double) sum) * 100.0;
		ratioCategoryC = (countCategoryC / (double) sum) * 100.0;
		infileDate = dateFormatter.format(new Date());

		// Create a simple pie chart
		DefaultPieDataset pieDataset = new DefaultPieDataset();

		pieDataset.setValue("Category A", new Double(ratioCategoryA));
		pieDataset.setValue("Category B", new Double(ratioCategoryB));
		pieDataset.setValue("Category C", new Double(ratioCategoryC));

		JFreeChart chart = ChartFactory.createPieChart("Category Pie Chart " + infileDate, // Title
				pieDataset, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
		);

		PiePlot plot = (PiePlot) chart.getPlot();

		plot.setBackgroundPaint(Color.white);
		plot.setCircular(true);

		// generate percentage on each category type on the pie chart
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}", NumberFormat.getNumberInstance(),
				NumberFormat.getPercentInstance()));

		plot.setNoDataMessage("No data available");

		try {
			ChartUtilities.saveChartAsJPEG(new File(filePath + File.separator + infileDate + ".jpg"), chart, 500, 300);
			System.out.println("creating chart.");
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
		}

	}

}
