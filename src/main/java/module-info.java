/**
 * Demo programs that showcase JavaFX libraries from JFree.
 */
module org.jfree.fx.demos {
    requires java.logging;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires org.jfree.chart3d;
    requires org.jfree.chart3d.fx;
    requires org.jfree.pdf;
    requires org.jfree.svg;
    requires org.jfree.jfreechart;
    requires org.jfree.chart.fx;
    requires org.jfree.fxgraphics2d;
    exports org.jfree.chart3d.fx.demo;
    exports org.jfree.chart.fx.demo;
    exports org.jfree.fx.demo;
}
