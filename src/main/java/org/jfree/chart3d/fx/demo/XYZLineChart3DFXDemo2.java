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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.plot.XYZPlot;

/**
 * An XYZ line chart demo for JavaFX.
 */
public class XYZLineChart3DFXDemo2 extends Application {

    public static Node createDemoNode() {
        XYZDataset dataset = createDataset();
        Chart3D chart = createChart(dataset);
        Chart3DViewer viewer = new Chart3DViewer(chart);
        return viewer;
    }
    
    private static XYZDataset<String> createDataset() {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                XYZLineChart3DFXDemo2.class.getResourceAsStream("fxrates.csv")));
        String line;
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        XYZSeries<String> aud = new XYZSeries<>("AUD");
        XYZSeries<String> brl = new XYZSeries<>("BRL");
        XYZSeries<String> chf = new XYZSeries<>("CHF");
        XYZSeries<String> cny = new XYZSeries<>("CNY");
        XYZSeries<String> eur = new XYZSeries<>("EUR");
        XYZSeries<String> gbp = new XYZSeries<>("GBP");
        XYZSeries<String> jpy = new XYZSeries<>("JPY");
        try {
            line = in.readLine();  // ignore the first line
	    while ((line = in.readLine()) != null) {
                String[] items = line.split(",");
		LocalDate d = LocalDate.parse(items[0]);
                aud.add(d.toEpochDay(), Double.valueOf(items[2]), 1);
                brl.add(d.toEpochDay(), Double.valueOf(items[4]), 2);
                chf.add(d.toEpochDay(), Double.valueOf(items[6]), 3);
                cny.add(d.toEpochDay(), Double.valueOf(items[8]), 4);
                eur.add(d.toEpochDay(), Double.valueOf(items[10]), 5);
                gbp.add(d.toEpochDay(), Double.valueOf(items[12]), 6);
                jpy.add(d.toEpochDay(), Double.valueOf(items[14]), 7);
            }
            dataset.add(aud);
            dataset.add(brl);
            dataset.add(chf);
            dataset.add(cny);
            dataset.add(eur);
            dataset.add(gbp);
            dataset.add(jpy);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return dataset;
    }
    
    private static Chart3D createChart(XYZDataset dataset) {
        Chart3D chart = Chart3DFactory.createXYZLineChart("XYZ Line Chart Demo", 
                "Orson Charts", dataset, "Day", "Index", "Station");
        chart.setChartBoxColor(new Color(255, 255, 255, 128));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(15, 10, 8));
        return chart;    
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: XYZLineChart3DFXDemo2.java");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

