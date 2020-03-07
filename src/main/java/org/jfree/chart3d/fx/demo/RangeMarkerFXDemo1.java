/* ===================
 * Orson Charts - Demo
 * ===================
 * 
 * Copyright (c) 2013-2020, Object Refinery Limited.
 * All rights reserved.
 *
 * https://github.com/jfree/jfree-fxdemos
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the Object Refinery Limited nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Note that the above terms apply to the demo source only, and not the 
 * Orson Charts library.
 * 
 */

package org.jfree.chart3d.fx.demo;

import static javafx.application.Application.launch;
import java.awt.Color;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.axis.NumberAxis3D;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics2d.Anchor2D;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.graphics3d.ViewPoint3D;
import org.jfree.chart3d.interaction.StandardXYZDataItemSelection;
import org.jfree.chart3d.interaction.XYZDataItemSelection;
import org.jfree.chart3d.label.StandardXYZItemLabelGenerator;
import org.jfree.chart3d.marker.RangeMarker;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.chart3d.renderer.xyz.ScatterXYZRenderer;
import org.jfree.chart3d.style.ChartStyler;
import org.jfree.chart3d.style.ChartStyles;

/**
 * A range marker demo for JavaFX.
 */
public class RangeMarkerFXDemo1 extends Application {

    public static Node createDemoNode() {
        XYZDataset dataset = createDataset();
        Chart3D chart = createChart(dataset);
        Chart3DViewer viewer = new Chart3DViewer(chart);
        return viewer;
    }

    /**
     * Creates a demo chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A demo chart. 
     */
    private static Chart3D createChart(XYZDataset dataset) {
        Chart3D chart = Chart3DFactory.createScatterChart("RangeMarkerDemo1", 
                null, dataset, "X", "Y", "Z");
        chart.setStyle(ChartStyles.createOrson1Style());
        
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10.0, 6.0, 10.0));

        ChartStyler styler = new ChartStyler(chart.getStyle());
        NumberAxis3D xAxis = (NumberAxis3D) plot.getXAxis();
        RangeMarker xMarker1 = new RangeMarker(60, 90, "X: 60 to 90");
        xMarker1.receive(styler);
        xMarker1.setFillColor(new Color(128, 128, 255, 128));
        xMarker1.setLabelAnchor(Anchor2D.BOTTOM_LEFT);
        xAxis.setMarker("X1", xMarker1);
        NumberAxis3D yAxis = (NumberAxis3D) plot.getYAxis();
        RangeMarker yMarker1 = new RangeMarker(0.002, 0.006, "Y: 0.002 to 0.006");
        yMarker1.receive(styler);
        yMarker1.setFillColor(new Color(128, 255, 128, 128));
        yAxis.setMarker("Y1", yMarker1);
        NumberAxis3D zAxis = (NumberAxis3D) plot.getZAxis();
        RangeMarker zMarker1 = new RangeMarker(20, 60, "Z: 20 to 60");
        zMarker1.setLabelAnchor(Anchor2D.TOP_LEFT);
        zMarker1.receive(styler);
        zMarker1.setFillColor(new Color(255, 128, 128, 128));
        zAxis.setMarker("Z1", zMarker1);
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        HighlightXYZColorSource colorSource = new HighlightXYZColorSource(
                plot.getDataset(), Color.RED, xMarker1.getRange(), 
                yMarker1.getRange(), zMarker1.getRange(), 
                chart.getStyle().getStandardColors());
        renderer.setColorSource(colorSource);
        StandardXYZItemLabelGenerator generator 
                = new StandardXYZItemLabelGenerator();
        XYZDataItemSelection selection = new StandardXYZDataItemSelection();
        generator.setItemSelection(selection);
        renderer.setItemLabelGenerator(generator);
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40));
        return chart;    
    }
    
    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     * 
     * @return A sample dataset.
     */
    private static XYZDataset<String> createDataset() {
        XYZSeries<String> s1 = createRandomSeries("S1", 10);
        XYZSeries<String> s2 = createRandomSeries("S2", 50);
        XYZSeries<String> s3 = createRandomSeries("S3", 150);
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        dataset.add(s1);
        dataset.add(s2);
        dataset.add(s3);
        return dataset;
    }
    
    private static XYZSeries<String> createRandomSeries(String name, int count) {
        XYZSeries<String> s = new XYZSeries<>(name);
        for (int i = 0; i < count; i++) {
            s.add(Math.random() * 100, Math.random() / 100, Math.random() * 100);
        }
        return s;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: RangeMarkerFXDemo1.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
