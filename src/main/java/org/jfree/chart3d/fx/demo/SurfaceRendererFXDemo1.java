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
import java.awt.Color;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.axis.ValueAxis3D;
import org.jfree.chart3d.data.Range;
import org.jfree.chart3d.data.function.Function3D;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.chart3d.renderer.GradientColorScale;
import org.jfree.chart3d.renderer.xyz.SurfaceRenderer;

/**
 * A surface renderer demo for JavaFX.
 */
public class SurfaceRendererFXDemo1 extends Application {
    
    /**
     * Creates and returns a node for the demo chart.
     * 
     * @return A node for the demo chart.
     */
    public static Node createDemoNode() {
        Chart3D chart = createChart();
        return new Chart3DViewer(chart);
    }
   
    /**
     * Creates a surface chart for the demo.
     * 
     * @return A surface chart. 
     */
    private static Chart3D createChart() {
        Function3D function = (double x, double z) -> Math.cos(x) * Math.sin(z);
        
        Chart3D chart = Chart3DFactory.createSurfaceChart(
                "SurfaceRendererDemo1", 
                "y = cos(x) * sin(z)", 
                function, "X", "Y", "Z");
 
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10, 5, 10));
        ValueAxis3D xAxis = plot.getXAxis();
        xAxis.setRange(-Math.PI, Math.PI);
        ValueAxis3D zAxis = plot.getZAxis();
        zAxis.setRange(-Math.PI, Math.PI);
        SurfaceRenderer renderer = (SurfaceRenderer) plot.getRenderer();
        renderer.setDrawFaceOutlines(false);
        renderer.setColorScale(new GradientColorScale(new Range(-1.0, 1.0), 
                Color.RED, Color.YELLOW));
        return chart;    
    }

    @Override
    public void start(Stage stage) {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: SurfaceRendererFXDemo1.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
}

