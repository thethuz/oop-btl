package game.grapgic.player;
//
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class PlayerGraphic {
  //private
  private Image player, player1;
  private Image[] playerMove=new Image[4];
  private Image[] playerAttack=new Image[4];
  private Animation playerAnimation;
  private Animation[] playerAtkAnimation = new Animation[4];
  private Animation [] playerMoveAnimation= new Animation[4];
  //private Animation ;
  private Graphics second;
  private URL base;
  public void Init(){
    player = getImage(base,"res/player.png");
    player1 = getImage(base,"res/player1.png");
    for(int i=0;i<=3;i++){
      playerMove[i]=getImage(base,"res/playerMove"+i+".png");
    }
    for(int i=0;i<=3;i++){
      playerAttack[i]=getImage(base,"res/playerAtk"+i+".png");
    }
  }
  private void setPlayerAnimation(){
    playerAnimation= new Animation();
    playerAnimation.addFrame(player,100);
    playerAnimation.addFrame(player1,100);
  }
  private void setPlayerMoveAnimation(){
    for (int i=0;i<=3;i++){
      playerMoveAnimation[i].addFrame(player,50);
      playerMoveAnimation[i].addFrame(playerMove[i],400);
      playerMoveAnimation[i].addFrame(player,50);
    }
  }
  private void setPlayerAttackAnimation(){
    for (int i=0;i<=3;i++){
      playerAtkAnimation[i].addFrame(player,50);
      playerAtkAnimation[i].addFrame(playerAttack[i],400);
      playerAtkAnimation[i].addFrame(player,50);
    }
  }
}
