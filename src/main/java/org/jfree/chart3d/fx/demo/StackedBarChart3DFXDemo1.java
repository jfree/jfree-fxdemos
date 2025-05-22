/* ===================
 * Orson Charts - Demo
 * ===================
 *
 * Copyright 2014-present, by David Gilbert. All rights reserved.
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
 *   - Neither the name of David Gilbert nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL DAVID GILBERT BE LIABLE FOR ANY
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
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.data.DefaultKeyedValues;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.data.category.StandardCategoryDataset3D;
import org.jfree.chart3d.fx.Chart3DViewer;

/**
 * A 3D stacked bar chart demo for JavaFX.
 */
public class StackedBarChart3DFXDemo1 extends Application {

    /**
     * Creates and returns a node for the demo chart.
     * 
     * @return A node for the demo chart.
     */
    public static Node createDemoNode() {
        CategoryDataset3D<String, String, String> dataset = createDataset();
        Chart3D chart = createChart(dataset);
        return new Chart3DViewer(chart);
    }

    /**
     * Creates a stacked bar chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A stacked bar chart. 
     */
    private static Chart3D createChart(CategoryDataset3D<String, String, String> dataset) {
        return Chart3DFactory.createStackedBarChart(
                "Stacked Bar Chart", "Put the data source here", dataset, null,
                null, "Value");
    }
    
    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     * 
     * @return A sample dataset.
     */
    private static CategoryDataset3D<String, String, String> createDataset() {
        
        StandardCategoryDataset3D<String, String, String> dataset 
                = new StandardCategoryDataset3D<>();

        DefaultKeyedValues<String, Double> s1 = new DefaultKeyedValues<>();
        s1.put("A", 4.0);
        s1.put("B", 2.0);
        s1.put("C", 3.0);
        s1.put("D", 5.0);
        s1.put("E", 2.0);
        s1.put("F", 1.0);
        DefaultKeyedValues<String, Double> s2 = new DefaultKeyedValues<>();
        s2.put("A", 1.0);
        s2.put("B", 2.0);
        s2.put("C", 3.0);
        s2.put("D", 2.0);
        s2.put("E", 3.0);
        s2.put("F", 1.0);
        DefaultKeyedValues<String, Double> s3 = new DefaultKeyedValues<>();
        s3.put("A", 6.0);
        s3.put("B", 6.0);
        s3.put("C", 6.0);
        s3.put("D", 4.0);
        s3.put("E", 4.0);
        s3.put("F", 4.0);
        DefaultKeyedValues<String, Double> s4 = new DefaultKeyedValues<>();
        s4.put("A", 9.0);
        s4.put("B", 8.0);
        s4.put("C", 7.0);
        s4.put("D", 6.0);
        s4.put("D", 3.0);
        s4.put("E", 4.0);
        s4.put("F", 6.0);
        DefaultKeyedValues<String, Double> s5 = new DefaultKeyedValues<>();
        s5.put("A", 9.0);
        s5.put("B", 8.0);
        s5.put("C", 7.0);
        s5.put("D", 6.0);
        s5.put("E", 7.0);
        s5.put("F", 9.0);

        dataset.addSeriesAsRow("Series 1", "Row 1", s1);
        dataset.addSeriesAsRow("Series 2", "Row 2", s2);
        dataset.addSeriesAsRow("Series 3", "Row 2", s3);
        dataset.addSeriesAsRow("Series 4", "Row 3", s4);
        dataset.addSeriesAsRow("Series 5", "Row 3", s5);
        return dataset;
    }

    @Override
    public void start(Stage stage) {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: StackedBarChart3DFXDemo1.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
