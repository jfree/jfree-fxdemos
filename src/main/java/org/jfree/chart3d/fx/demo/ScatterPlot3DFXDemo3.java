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
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Colors;
import org.jfree.chart3d.Orientation;
import org.jfree.chart3d.data.DataUtils;
import org.jfree.chart3d.data.JSONUtils;
import org.jfree.chart3d.data.KeyedValues3D;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.ViewPoint3D;
import org.jfree.chart3d.legend.LegendAnchor;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.chart3d.renderer.xyz.ScatterXYZRenderer;
import org.jfree.chart3d.style.StandardChartStyle;
import org.jfree.chart3d.table.TextElement;

/**
 * A scatter plot demo for JavaFX.
 */
public class ScatterPlot3DFXDemo3 extends Application {
    
    /**
     * Returns a panel containing the content for the demo.  This method is
     * used across all the individual demo applications to allow aggregation 
     * into a single "umbrella" demo (OrsonChartsDemo).
     * 
     * @return A panel containing the content for the demo.
     */
    public static Node createDemoNode() {
        
        XYZDataset[] datasets = createDatasets();
        Chart3D chart1 = createChart(
                "Iris Dataset : Combination 1", datasets[0], "Sepal Length", 
                "Sepal Width", "Petal Length");
        chart1.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(120));
        Chart3DViewer viewer1 = new Chart3DViewer(chart1);
        Chart3D chart2 = createChart(
                "Iris Dataset : Combination 2", datasets[1], 
                "Sepal Length", "Sepal Width", "Petal Width");
        chart2.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(120));
        Chart3DViewer viewer2 = new Chart3DViewer(chart2);
        Chart3D chart3 = createChart(
                "Iris Dataset : Combination 3", datasets[2], 
                "Sepal Length", "Petal Width", "Petal Length");
        chart3.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(120));
        Chart3DViewer viewer3 = new Chart3DViewer(chart3);
        Chart3D chart4 = createChart(
                "Iris Dataset : Combination 4", datasets[3], 
                "Sepal Width", "Petal Width", "Petal Length");
        chart4.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(120));
        Chart3DViewer viewer4 = new Chart3DViewer(chart4);
        GridPane pane = new GridPane();
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(50);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(50);
        pane.getColumnConstraints().addAll(c1, c2);
        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(50);
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(50);
        pane.getRowConstraints().addAll(r1, r2);
        pane.add(viewer1, 0, 0);
        pane.add(viewer2, 0, 1);
        pane.add(viewer3, 1, 0);
        pane.add(viewer4, 1, 1);
        return pane;
    }

    public static XYZDataset[] createDatasets() {
        XYZDataset[] datasets = new XYZDataset[4];
        datasets[0] = createDataset("sepal length", "sepal width", 
                "petal length");
        datasets[1] = createDataset("sepal length", "sepal width", 
                "petal width");
        datasets[2] = createDataset("sepal length", "petal width", 
                "petal length");
        datasets[3] = createDataset("sepal width", "petal width", 
                "petal length");
        return datasets;
    }
    
    public static Chart3D createChart(String title, XYZDataset dataset, 
            String xLabel, String yLabel, String zLabel) {
        Chart3D chart = Chart3DFactory.createScatterChart(null, null, 
                dataset, xLabel, yLabel, zLabel);
        TextElement title1 = new TextElement(title);
        title1.setFont(StandardChartStyle.createDefaultFont(Font.PLAIN, 16));
        chart.setTitle(title1);
        chart.setLegendAnchor(LegendAnchor.BOTTOM_LEFT);
        chart.setLegendOrientation(Orientation.VERTICAL);
        XYZPlot plot = (XYZPlot) chart.getPlot();
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(Colors.createIntenseColors());
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40));
        return chart;
    }
    
    /**
     * Reads a dataset from the file iris.txt.
     * 
     * @return A sample dataset.
     */
    private static XYZDataset<String> createDataset(String xKey, String yKey, 
            String zKey) {
        Reader in = new InputStreamReader(
                ScatterPlot3DFXDemo3.class.getResourceAsStream("iris.txt"));
        KeyedValues3D<String, String, String, Number> data;
        try {
            data = JSONUtils.readKeyedValues3D(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return DataUtils.extractXYZDatasetFromColumns(data, xKey, yKey, zKey);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: ScatterPlotFXDemo3.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
