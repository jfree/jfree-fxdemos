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
import org.jfree.chart3d.Colors;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.data.category.StandardCategoryDataset3D;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.plot.CategoryPlot3D;
import org.jfree.chart3d.renderer.category.StackedBarRenderer3D;

/**
 * A 3D stacked bar chart demo for JavaFX.
 */
public class StackedBarChart3DFXDemo2 extends Application {

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

        dataset.addValue(197, "Agricultural", "R1", "Brazil");
        dataset.addValue(64, "Domestic", "R1", "Brazil");
        dataset.addValue(57, "Industrial", "R1", "Brazil");
        
        dataset.addValue(339, "Agricultural", "R1", "Indonesia");
        dataset.addValue(30, "Domestic", "R1", "Indonesia");
        dataset.addValue(4, "Industrial", "R1", "Indonesia");
        
        dataset.addValue(279, "Agricultural", "R1", "China");
        dataset.addValue(27, "Domestic", "R1", "China");
        dataset.addValue(107, "Industrial", "R1", "China");

        dataset.addValue(92, "Agricultural", "R1", "Germany");
        dataset.addValue(55, "Domestic", "R1", "Germany");
        dataset.addValue(313, "Industrial", "R1", "Germany");

        dataset.addValue(96, "Agricultural", "R1", "Russia");
        dataset.addValue(102, "Domestic", "R1", "Russia");
        dataset.addValue(337, "Industrial", "R1", "Russia");

        dataset.addValue(403, "Agricultural", "R1", "Turkey");
        dataset.addValue(82, "Domestic", "R1", "Turkey");
        dataset.addValue(60, "Industrial", "R1", "Turkey");
        
        dataset.addValue(536, "Agricultural", "R1", "Bangladesh");
        dataset.addValue(17, "Domestic", "R1", "Bangladesh");
        dataset.addValue(6, "Industrial", "R1", "Bangladesh");
        
        dataset.addValue(508, "Agricultural", "R1", "India");
        dataset.addValue(47, "Domestic", "R1", "India");
        dataset.addValue(30, "Industrial", "R1", "India");
        
        dataset.addValue(428, "Agricultural", "R1", "Japan");
        dataset.addValue(138, "Domestic", "R1", "Japan");
        dataset.addValue(124, "Industrial", "R1", "Japan");

        dataset.addValue(325, "Agricultural", "R1", "Italy");
        dataset.addValue(130, "Domestic", "R1", "Italy");
        dataset.addValue(268, "Industrial", "R1", "Italy");
        
        dataset.addValue(569, "Agricultural", "R1", "Mexico");
        dataset.addValue(126, "Domestic", "R1", "Mexico");
        dataset.addValue(37, "Industrial", "R1", "Mexico");

        dataset.addValue(576, "Agricultural", "R1", "Vietnam");
        dataset.addValue(68, "Domestic", "R1", "Vietnam");
        dataset.addValue(203, "Industrial", "R1", "Vietnam");
        
        dataset.addValue(794, "Agricultural", "R1", "Egypt");
        dataset.addValue(74, "Domestic", "R1", "Egypt");
        dataset.addValue(55, "Industrial", "R1", "Egypt");

        dataset.addValue(954, "Agricultural", "R1", "Iran");
        dataset.addValue(21, "Domestic", "R1", "Iran");
        dataset.addValue(73, "Industrial", "R1", "Iran");

        dataset.addValue(1029, "Agricultural", "R1", "Pakistan");
        dataset.addValue(21, "Domestic", "R1", "Pakistan");
        dataset.addValue(21, "Industrial", "R1", "Pakistan");

        dataset.addValue(1236, "Agricultural", "R1", "Thailand");
        dataset.addValue(26, "Domestic", "R1", "Thailand");
        dataset.addValue(26, "Industrial", "R1", "Thailand");

        dataset.addValue(165, "Agricultural", "R1", "Canada");
        dataset.addValue(274, "Domestic", "R1", "Canada");
        dataset.addValue(947, "Industrial", "R1", "Canada");
        
        dataset.addValue(1363, "Agricultural", "R1", "Iraq");
        dataset.addValue(44, "Domestic", "R1", "Iraq");
        dataset.addValue(74, "Industrial", "R1", "Iraq");
        
        dataset.addValue(656, "Agricultural", "R1", "US");
        dataset.addValue(208, "Domestic", "R1", "US");
        dataset.addValue(736, "Industrial", "R1", "US");
        
        dataset.addValue(2040, "Agricultural", "R1", "Uzbekistan");
        dataset.addValue(110, "Domestic", "R1", "Uzbekistan");
        dataset.addValue(44, "Industrial", "R1", "Uzbekistan");
        
        return dataset;
    }

    /**
     * Creates a stacked bar chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A stacked bar chart. 
     */
    public static Chart3D createChart(CategoryDataset3D<String, String, String> dataset) {
        Chart3D chart = Chart3DFactory.createStackedBarChart(
                "Water Usage Chart", 
                "Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply", 
                dataset, null, null, "Cubic meters / person / year");
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.setDimensions(new Dimension3D(14, 6.5, 2));
        plot.getRowAxis().setVisible(false);
        StackedBarRenderer3D renderer 
                = (StackedBarRenderer3D) plot.getRenderer();
        renderer.setBarZWidth(0.3);
        renderer.setColors(Colors.createBlueOceanColors());
        chart.getViewPoint().moveUpDown(Math.PI / 30);
        return chart;
    }
    
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

    @Override
    public void start(Stage stage) {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: StackedBarChart3DFXDemo2.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
