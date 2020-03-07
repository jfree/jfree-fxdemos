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

import java.awt.Color;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.axis.StandardCategoryAxis3D;
import org.jfree.chart3d.data.KeyedValues3DItemKey;
import org.jfree.chart3d.data.KeyedValues3DItemKeys;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.fx.interaction.FXChart3DMouseEvent;
import org.jfree.chart3d.graphics3d.Object3D;
import org.jfree.chart3d.graphics3d.RenderedElement;
import org.jfree.chart3d.interaction.InteractiveElementType;
import org.jfree.chart3d.interaction.KeyedValues3DItemSelection;
import org.jfree.chart3d.interaction.StandardKeyedValues3DItemSelection;
import org.jfree.chart3d.label.StandardCategoryItemLabelGenerator;
import org.jfree.chart3d.legend.LegendAnchor;
import org.jfree.chart3d.marker.CategoryMarker;
import org.jfree.chart3d.plot.CategoryPlot3D;
import org.jfree.chart3d.renderer.category.BarRenderer3D;
import org.jfree.chart3d.style.ChartStyler;

/**
 * A demo showing category markers on a 3D bar chart plus many elements of
 * chart interactivity.
 */
@SuppressWarnings("serial")
public class CategoryMarkerFXDemo1 extends Application {
    
    static class CustomDemoNode extends BorderPane {
        
        private final Chart3DViewer chartViewer;

        private String selectedRowKey;
        
        private String selectedColumnKey;
        
        private final CheckBox itemLabelCheckBox;
        
        public CustomDemoNode(Chart3D chart) {
            this.chartViewer = new Chart3DViewer(chart);
            this.chartViewer.addEventHandler(FXChart3DMouseEvent.MOUSE_CLICKED, 
                    (FXChart3DMouseEvent event) -> {
                        chartMouseClicked(event);
                    });
            this.selectedRowKey = "Apple";
            this.selectedColumnKey = "Q4/12";
            this.itemLabelCheckBox = new CheckBox("Show item labels?");
            this.itemLabelCheckBox.setOnAction(e -> { 
                updateItemSelection(selectedRowKey, selectedColumnKey);
                chart.setNotify(true);
            });
            setCenter(this.chartViewer);
            HBox container = new HBox();
            container.setAlignment(Pos.CENTER);
            container.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
            container.getChildren().add(this.itemLabelCheckBox);
            setBottom(container);
        }
        
        @SuppressWarnings("unchecked")
        private void updateColorSource(String selectedRow, 
                String selectedColumn) {
            HighlightCategoryColorSource colorSource 
                    = (HighlightCategoryColorSource) 
                    getRenderer().getColorSource();
            int rowIndex = getPlot().getDataset().getRowIndex(selectedRow);
            int columnIndex = getPlot().getDataset().getColumnIndex(
                    selectedColumn);
            colorSource.setHighlightRowIndex(rowIndex);
            colorSource.setHighlightColumnIndex(columnIndex);
        }
        
        @SuppressWarnings("unchecked")
        private void updateItemSelection(String selectedRow, 
                String selectedColumn) {
            StandardKeyedValues3DItemSelection itemSelection 
                    = (StandardKeyedValues3DItemSelection) getItemSelection();
            itemSelection.clear();
            if (this.itemLabelCheckBox.isSelected()) {
                itemSelection.addAll(KeyedValues3DItemKeys.itemKeysForColumn(
                        getPlot().getDataset(), selectedColumn));
                itemSelection.addAll(KeyedValues3DItemKeys.itemKeysForRow(
                        getPlot().getDataset(), selectedRow));
            }
        }
        
        private CategoryPlot3D getPlot() {
            Chart3D chart = this.chartViewer.getChart();
            return (CategoryPlot3D) chart.getPlot();
        }
        
        private BarRenderer3D getRenderer() {
            return (BarRenderer3D) getPlot().getRenderer();        
        }
        
        private KeyedValues3DItemSelection getItemSelection() {
            StandardCategoryItemLabelGenerator generator 
                    = (StandardCategoryItemLabelGenerator) 
                    getRenderer().getItemLabelGenerator();
            return generator.getItemSelection();
        }

        private void handleSelectItem(Comparable rowKey, Comparable columnKey) {
            Chart3D chart = this.chartViewer.getChart();
            chart.setNotify(false);
            CategoryPlot3D plot = getPlot();
            StandardCategoryAxis3D rowAxis 
                    = (StandardCategoryAxis3D) plot.getRowAxis();
            CategoryMarker rowMarker = rowAxis.getMarker("RM1");
            if (rowMarker == null) {
                rowMarker = new CategoryMarker("");
                rowMarker.receive(new ChartStyler(chart.getStyle()));
            }
            StandardCategoryAxis3D columnAxis 
                    = (StandardCategoryAxis3D) plot.getColumnAxis();
            CategoryMarker columnMarker = columnAxis.getMarker("CM1");
            if (columnMarker == null) {
                columnMarker = new CategoryMarker("");
                columnMarker.receive(new ChartStyler(chart.getStyle()));
            }
            this.selectedRowKey = rowKey.toString();
            this.selectedColumnKey = columnKey.toString();
            rowMarker.setCategory(this.selectedRowKey);
            columnMarker.setCategory(this.selectedColumnKey);
            updateColorSource(this.selectedRowKey, this.selectedColumnKey);
            updateItemSelection(this.selectedRowKey, this.selectedColumnKey);
            chart.setNotify(true);
        }
        
        private void handleSelectRow(Comparable rowKey) {
            handleSelectItem(rowKey, this.selectedColumnKey);
        }
        
        private void handleSelectColumn(Comparable columnKey) {
            handleSelectItem(this.selectedRowKey, columnKey);
        }
        
        public void chartMouseClicked(FXChart3DMouseEvent event) {
            RenderedElement element = event.getElement();
            if (element == null) {
                return;
            }
            // first handle clicks on data items
            KeyedValues3DItemKey key = (KeyedValues3DItemKey) 
                    element.getProperty(Object3D.ITEM_KEY);
            if (key != null) {
                handleSelectItem(key.getRowKey(), key.getColumnKey());
            } else {
                if (InteractiveElementType.CATEGORY_AXIS_TICK_LABEL.equals(
                        element.getType())) {
                    String label = (String) element.getProperty("label");
                    String axisStr = (String) element.getProperty("axis");
                    if (axisStr.equals("row")) {
                        handleSelectRow(label);
                    } else { // column axis
                        handleSelectColumn(label);
                    }
                } else if (InteractiveElementType.LEGEND_ITEM.equals(
                        element.getType())) {
                    Comparable<?> seriesKey = (Comparable<?>) 
                            element.getProperty(Chart3D.SERIES_KEY);
                    // the row keys are the same as the series keys in 
                    // this chart
                    handleSelectRow(seriesKey);
                } else {
                    //JOptionPane.showMessageDialog(this, 
                    //        Chart3D.renderedElementToString(element));
                }
            }
        }

    }

    /**
     * Creates a bar chart using the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A bar chart.
     */
    private static Chart3D createChart(CategoryDataset3D dataset) {
        Chart3D chart = Chart3DFactory.createBarChart("Quarterly Revenues", 
                "For some large IT companies", dataset, null, "Quarter", 
                "$billion Revenues");
        chart.setChartBoxColor(new Color(255, 255, 255, 127));
        chart.setLegendAnchor(LegendAnchor.BOTTOM_RIGHT);
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.setGridlinePaintForValues(Color.BLACK);
        StandardCategoryAxis3D rowAxis 
                = (StandardCategoryAxis3D) plot.getRowAxis();
        rowAxis.setMarker("RM1", new CategoryMarker("Apple"));
        StandardCategoryAxis3D columnAxis 
                = (StandardCategoryAxis3D) plot.getColumnAxis();
        columnAxis.setMarker("CM1", new CategoryMarker("Q4/12"));
        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        StandardCategoryItemLabelGenerator itemLabelGenerator = 
                new StandardCategoryItemLabelGenerator(
                StandardCategoryItemLabelGenerator.VALUE_TEMPLATE);
        StandardKeyedValues3DItemSelection itemSelection 
                = new StandardKeyedValues3DItemSelection();
        itemLabelGenerator.setItemSelection(itemSelection);
        renderer.setItemLabelGenerator(itemLabelGenerator);
        HighlightCategoryColorSource colorSource 
                = new HighlightCategoryColorSource();
        colorSource.setHighlightRowIndex(3);
        colorSource.setHighlightColumnIndex(6);
        renderer.setColorSource(colorSource);
        return chart;
    }
 
    public static Node createDemoNode() {
        CategoryDataset3D dataset = SampleData.createCompanyRevenueDataset();
        Chart3D chart = createChart(dataset);
        CustomDemoNode node = new CustomDemoNode(chart);
        return node;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane sp = new StackPane();
        sp.getChildren().add(createDemoNode());
        Scene scene = new Scene(sp, 768, 512);
        stage.setScene(scene);
        stage.setTitle("Orson Charts: CategoryMarkerFXDemo1.java");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
