package UIclass;

import javafx.scene.Group;

public class RoadTram2 extends Group {

    final private SimpleRoadTram simpleRoadTramBottomRight = new SimpleRoadTram(0, 500, 550);
    final private SimpleRoadTram simpleRoadTramBottomLeft = new SimpleRoadTram(730, 500, 550);
    final private ObliqueRoadTram obliqueRoadTramBottonLeft = new ObliqueRoadTram(730, 340, 160);
    final private ObliqueRoadTram obliqueRoadTramBottonRight = new ObliqueRoadTram(530, 340, 160);
   public RoadTram2(){
       obliqueRoadTramBottonRight.getGroup().setRotate(180);
       this.getChildren().add(0, simpleRoadTramBottomLeft);
       this.getChildren().add(1, obliqueRoadTramBottonLeft);
       this.getChildren().add(2, obliqueRoadTramBottonRight);
       this.getChildren().add(3, simpleRoadTramBottomRight);
   }
}
