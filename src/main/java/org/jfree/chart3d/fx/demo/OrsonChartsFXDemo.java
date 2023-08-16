/* ===================
 * Orson Charts - Demo
 * ===================
 *
 * Copyright 2014-2022 by David Gilbert. All rights reserved.
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

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

import com.jpro.webapi.HTMLView;
import com.jpro.webapi.WebAPI;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.data.Dataset3D;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.fx.Chart3DCanvas;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.fx.interaction.FXChart3DMouseEvent;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.graphics3d.Drawable3D;

/**
 * Demo application for Orson Charts in JavaFX.
 */
public class OrsonChartsFXDemo extends Application {

    private static final Logger LOGGER = Logger.getLogger(OrsonChartsFXDemo.class.getName());

    private static final String PREFIX = "org.jfree.chart3d.fx.demo.";
    
    private final Map<String, DemoDescription> descriptions;
    
    private SplitPane splitter;
        
    private StackPane chartDescription;

    /**
     * Creates a new demo instance.
     */
    public OrsonChartsFXDemo() {
        super();    
        this.descriptions = new HashMap<>();
    }
    
    @Override
    public void start(Stage stage) {
       
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("Demos");
        tab1.setClosable(false);
        
        SplitPane sp = new SplitPane();
        final StackPane sp1 = new StackPane();
        sp1.getChildren().add(createTreeView());
        final BorderPane sp2 = new BorderPane();
        sp2.setCenter(createChartPane());
 
        sp.getItems().addAll(sp1, sp2);
        sp.setDividerPositions(0.3f, 0.6f);
        tab1.setContent(sp);
        tabPane.getTabs().add(tab1);        
 
        Tab tab2 = new Tab();
        tab2.setText("About");
        tab2.setClosable(false);
        Node browser = createHTMLNode("/org/jfree/chart3d/fx/demo/about.html");
        tab2.setContent(browser);
        tabPane.getTabs().add(tab2);

        Scene scene = new Scene(tabPane, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Orson Charts JavaFX Demo");
        stage.show();
    }

    private Node createHTMLNode(String resource) {
        if(WebAPI.isBrowser()) {
            try {
                HTMLView htmlView = new HTMLView();
                InputStream is = getClass().getResourceAsStream(resource);

                String content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                htmlView.setContent(content);
                return htmlView;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(getClass().getResource(resource).toString());
            return browser;
        }
    }
    
    private static final String ABOUT_PREFIX = "/org/jfree/chart3d/fx/demo/";
    
    private StackPane createTreeView() {
        TreeItem<String> rootItem = new TreeItem<> ("Orson Charts", null);
        rootItem.setExpanded(true);
        TreeItem<String> categoryChartsNode = new TreeItem<>("Category Charts");
        categoryChartsNode.setExpanded(true);
        rootItem.getChildren().add(categoryChartsNode);
        
        DemoDescription d = new DemoDescription(PREFIX + "AreaChart3DFXDemo1", 
                "AreaChart3DFXDemo1.java", ABOUT_PREFIX + "AreaChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        TreeItem<String> n = new TreeItem<>(d.getFileName());
        categoryChartsNode.getChildren().add(n);
        
        d = new DemoDescription(PREFIX + "AreaChart3DFXDemo2", 
                "AreaChart3DFXDemo2.java", ABOUT_PREFIX + "AreaChart3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);
        
        d = new DemoDescription(PREFIX + "BarChart3DFXDemo1", 
                "BarChart3DFXDemo1.java", ABOUT_PREFIX + "BarChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "BarChart3DFXDemo2", 
                "BarChart3DFXDemo2.java", ABOUT_PREFIX + "BarChart3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "CategoryMarkerFXDemo1", 
                "CategoryMarkerFXDemo1.java", ABOUT_PREFIX 
                + "CategoryMarkerFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "CategoryMarkerFXDemo2",
                "CategoryMarkerFXDemo2.java", ABOUT_PREFIX
                + "CategoryMarkerFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "LineChart3DFXDemo1",
                "LineChart3DFXDemo1.java", ABOUT_PREFIX + "LineChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "LineChart3DFXDemo2", 
                "LineChart3DFXDemo2.java", ABOUT_PREFIX + "LineChart3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "StackedBarChart3DFXDemo1", 
                "StackedBarChart3DFXDemo1.java", 
                ABOUT_PREFIX + "StackedBarChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "StackedBarChart3DFXDemo2", 
                "StackedBarChart3DFXDemo2.java", 
                ABOUT_PREFIX + "StackedBarChart3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "StackedBarChart3DFXDemo3", 
                "StackedBarChart3DFXDemo3.java", 
                ABOUT_PREFIX + "StackedBarChart3DFXDemo3.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        categoryChartsNode.getChildren().add(n);

        TreeItem<String> pieChartsNode = new TreeItem<>("Pie Charts");
        rootItem.getChildren().add(pieChartsNode);
        
        d = new DemoDescription(PREFIX + "PieChart3DFXDemo1", 
                "PieChart3DFXDemo1.java", 
                ABOUT_PREFIX + "PieChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        pieChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "PieChart3DFXDemo2", 
                "PieChart3DFXDemo2.java", ABOUT_PREFIX + "PieChart3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        pieChartsNode.getChildren().add(n);

        TreeItem<String> xyzChartsNode = new TreeItem<>("XYZ Charts");
        rootItem.getChildren().add(xyzChartsNode);

        d = new DemoDescription(PREFIX + "RangeMarkerFXDemo1", 
                "RangeMarkerFXDemo1.java", ABOUT_PREFIX + "RangeMarkerFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "ScatterPlot3DFXDemo1", 
                "ScatterPlot3DFXDemo1.java", 
                ABOUT_PREFIX + "ScatterPlot3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "ScatterPlot3DFXDemo2", 
                "ScatterPlot3DFXDemo2.java", 
                ABOUT_PREFIX + "ScatterPlot3DFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "ScatterPlot3DFXDemo3", 
                "ScatterPlot3DFXDemo3.java", 
                ABOUT_PREFIX + "ScatterPlot3DFXDemo3.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "SurfaceRendererFXDemo1", 
                "SurfaceRendererFXDemo1.java",
                ABOUT_PREFIX + "SurfaceRendererFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "SurfaceRendererFXDemo2", 
                "SurfaceRendererFXDemo2.java", 
                ABOUT_PREFIX + "SurfaceRendererFXDemo2.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "XYZBarChart3DFXDemo1", 
                "XYZBarChart3DFXDemo1.java", 
                ABOUT_PREFIX + "XYZBarChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);

        d = new DemoDescription(PREFIX + "XYZLineChart3DFXDemo1", 
                "XYZLineChart3DFXDemo1.java", 
                ABOUT_PREFIX + "XYZLineChart3DFXDemo1.html");
        this.descriptions.put(d.getFileName(), d);
        n = new TreeItem<>(d.getFileName());        
        xyzChartsNode.getChildren().add(n);
        
        TreeView<String> tree = new TreeView<> (rootItem);
        tree.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends TreeItem<String>> observableValue, 
                    TreeItem<String> oldItem, TreeItem<String> newItem) -> selectDemo(newItem.getValue()));
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        return root;
    }
    
    private Method getMethod(String name, Class c) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method: methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Adjusts the zoom to fit the chart within the specified dimensions.
     * 
     * @param drawable  the drawable ({@code null} not permitted).
     * @param size  the new dimensions ({@code null} not permitted).
     */
    public void zoomToFit(Drawable3D drawable, Dimension size) {
        double margin = 0.25;
        int w = (int) (size.getWidth() * (1.0 - margin));
        int h = (int) (size.getHeight() * (1.0 - margin));
        Dimension2D target = new Dimension(w, h);
        Dimension3D d3d = drawable.getDimensions();
        float distance = drawable.getViewPoint().optimalDistance(target, 
                d3d, drawable.getProjDistance());
        drawable.getViewPoint().setRho(distance);        
    }

    private void selectDemo(String name) {
        DemoDescription demoDesc = this.descriptions.get(name);
        if (demoDesc != null) {
            try {
                BorderPane borderPane = (BorderPane) this.splitter.getItems().get(0);
                Node oldnode = borderPane.getCenter();
                if (oldnode instanceof Chart3DViewer) {
                    Chart3DViewer c = (Chart3DViewer) oldnode;
                    c.prefWidthProperty().unbind();
                    c.prefHeightProperty().unbind();
                }
               
                // get the class by reflection
                Class<?> c = Class.forName(demoDesc.getClassName());
                Method m0 = getMethod("createDemoNode", c);
                if (m0 != null) {
                    Node node = (Node) m0.invoke(null, (Object[]) null);
                    borderPane.setCenter(node);
                    if (node instanceof Chart3DViewer) {
                        Chart3DViewer v = (Chart3DViewer) node;
                        v.addEventHandler(FXChart3DMouseEvent.MOUSE_CLICKED, (FXChart3DMouseEvent event) -> System.out.println(event.getElement()));
                        v.prefWidthProperty().bind(borderPane.widthProperty());
                        v.prefHeightProperty().bind(borderPane.heightProperty());
                        zoomToFit(v.getChart(), new Dimension(
                                (int) borderPane.getWidth(), 
                                (int) borderPane.getHeight()));
                    } 
              
                } else {
                    Method m1 = getMethod("createDataset", c);
                    Method m2 = getMethod("createChart", c);

                    Dataset3D dataset = (Dataset3D) m1.invoke(null, (Object[]) null);
                    Chart3D chart = (Chart3D) m2.invoke(null, new Object[] { dataset });
                
                    Chart3DCanvas canvas = new Chart3DCanvas(chart);
                    borderPane.setCenter(canvas);
                    canvas.widthProperty().bind(borderPane.widthProperty());
                    canvas.heightProperty().bind(borderPane.heightProperty());                
                    canvas.zoomToFit(canvas.getWidth(), canvas.getHeight());
                }
                //String urlStr = c.getResource(demoDesc.getDescriptionFileName()).toString();
                //this.chartDescription.getEngine().load(urlStr);
                this.chartDescription.getChildren().clear();
                this.chartDescription.getChildren().add(createHTMLNode(demoDesc.getDescriptionFileName()));
                
            } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

        }
     
    }
    
    private SplitPane createChartPane() {
        CategoryDataset3D<String, String, String> dataset = SampleData.createCompanyRevenueDataset();
        Chart3D chart = AreaChart3DFXDemo1.createChart(dataset);
        Chart3DViewer viewer = new Chart3DViewer(chart);
      
        this.splitter = new SplitPane();
        splitter.setOrientation(Orientation.VERTICAL);
        final BorderPane borderPane = new BorderPane();
        borderPane.setCenter(viewer);
        
       // Bind canvas size to stack pane size.
        viewer.prefWidthProperty().bind(borderPane.widthProperty());
        viewer.prefHeightProperty().bind(borderPane.heightProperty());

        final StackPane sp2 = new StackPane();
        
        this.chartDescription = new StackPane();
        this.chartDescription.getChildren().add(createHTMLNode("/org/jfree/chart3d/fx/demo/AreaChart3DFXDemo1.html"));
        sp2.getChildren().add(chartDescription);

        splitter.getItems().addAll(borderPane, sp2);
        splitter.setDividerPositions(0.70f, 0.30f);
        return splitter;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
