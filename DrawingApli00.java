package Kadai07;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLTransactionRollbackException;
import java.awt.geom.Path2D;

public class DrawingApli00 extends Frame implements ActionListener, AdjustmentListener {
  // ■ フィールド変数
  Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13; // フレームに配置するボタンの宣言
  Panel  pnl;                // ボタン配置用パネルの宣言
  MyCanvas mc;               // 別途作成した MyCanvas クラス型の変数の宣言
  Panel scr;                 //ボタン配置用のパネルの宣言
  Label lb1, lb2, lb3, lb4;  //スクロールバーのラベルの宣言
  Scrollbar sbar1, sbar2, sbar3, sbar4;//スクロールバーの宣言
  int red = 0, green = 0, blue = 0; //色の値の宣言
  int futosa = 0;//線の太さの宣言

  // ■ main メソッド（スタート地点）
  public static void main(String [] args) {
    DrawingApli00 da = new DrawingApli00(); 
  }

  // ■ コンストラクタ
  DrawingApli00() {
    super("Drawing Appli");
    this.setSize(1500, 900); 

    pnl = new Panel();       // Panel のオブジェクト（実体）を作成
    mc = new MyCanvas(this); // mc のオブジェクト（実体）を作成
    scr = new Panel();      //Panel のオブジェクト（実体）を作成

    this.setLayout(new BorderLayout()); // レイアウト方法の指定
    this.add(pnl, BorderLayout.EAST);         // 右側に パネルを配置
    this.add(mc,  BorderLayout.CENTER);       // 左側に mc （キャンバス）を配置
    this.add(scr,  BorderLayout.NORTH);      //上側にスクロールバーを表示
                                         
    pnl.setLayout(new GridLayout(9,1));  // ボタンを配置するため，９行１列のグリッドをパネル上にさらに作成
    bt1 = new Button("Free Hand"); bt1.addActionListener(this); pnl.add(bt1);// ボタンを順に配置
    bt2 = new Button("Line");      bt2.addActionListener(this); pnl.add(bt2);
    bt3 = new Button("Rectangle"); bt3.addActionListener(this); pnl.add(bt3);
    bt4 = new Button("Oval");      bt4.addActionListener(this); pnl.add(bt4);
    bt5 = new Button("FillRectangle");      bt5.addActionListener(this); pnl.add(bt5);
    bt6 = new Button("FillOval");      bt6.addActionListener(this); pnl.add(bt6);
    bt7 = new Button("Clear");      bt7.addActionListener(this); pnl.add(bt7);
    bt8 = new Button("Eraser");      bt8.addActionListener(this); pnl.add(bt8);
    bt9= new Button("Rhombus");      bt9.addActionListener(this); pnl.add(bt9);
    bt10 = new Button("Triangle");      bt10.addActionListener(this); pnl.add(bt10);
    bt11 = new Button("Curve");      bt11.addActionListener(this); pnl.add(bt11);
    bt12 = new Button("Curve vertex");      bt12.addActionListener(this); pnl.add(bt12);

    scr.setLayout(new GridLayout(2,2));

    lb1 = new Label("Line Width", Label.CENTER); 
    scr.add(lb1); // ラベルを配置

    sbar1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 50);
    sbar1.addAdjustmentListener(this); 
    scr.add(sbar1); // スクロールバーを配置

    lb2 = new Label("Red", Label.CENTER); 
    scr.add(lb2); // ラベルを配置

    sbar2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar2.addAdjustmentListener(this); 
    scr.add(sbar2); // スクロールバーを配置

    lb3 = new Label("Green", Label.CENTER); 
    scr.add(lb3); // ラベルを配置

    sbar3 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar3.addAdjustmentListener(this); 
    scr.add(sbar3); // スクロールバーを配置

    lb4 = new Label("Blue", Label.CENTER); 
    scr.add(lb4); // ラベルを配置

    sbar4 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
    sbar4.addAdjustmentListener(this); 
    scr.add(sbar4); // スクロールバーを配置


    this.setVisible(true); //可視化
  }

  // ■ メソッド
  // ActionListener を実装しているため、例え内容が空でも必ず記述しなければならない
  public void actionPerformed(ActionEvent e){ // フレーム上で生じたイベントを e で取得
    if (e.getSource() == bt1)      // もしイベントが bt1 で生じたなら
      mc.mode=1;                   // モードを１に
    else if (e.getSource() == bt2) // もしイベントが bt2 で生じたなら
      mc.mode=2;                   // モードを２に
    else if (e.getSource() == bt3) // もしイベントが bt3 で生じたなら
      mc.mode=3;                   // モードを３に
    else if (e.getSource() == bt4) // もしイベントが bt4 で生じたなら
      mc.mode=4;                   // モードを４に
    else if (e.getSource() == bt5)// もしイベントが bt5 で生じたなら
      mc.mode=5;                  // モードを5に
    else if (e.getSource() == bt6) // もしイベントが bt6 で生じたなら
      mc.mode=6;                    // モードを6に
    else if (e.getSource() == bt7)// もしイベントが bt7 で生じたなら 
      mc.mode=7;                  // モードを7に
    else if (e.getSource() == bt8)// もしイベントが bt8 で生じたなら 
      mc.mode=8;                  // モードを8に
    else if (e.getSource() == bt9)// もしイベントが bt9 で生じたなら 
      mc.mode=9;                  // モードを9に
    else if (e.getSource() == bt10)// もしイベントが bt10 で生じたなら 
      mc.mode=10;                  // モードを10に
    else if (e.getSource() == bt11)// もしイベントが bt11 で生じたなら 
      mc.mode=11;                  // モードを11に
    else if (e.getSource() == bt12)// もしイベントが bt12 で生じたなら 
      mc.mode=12;                  // モードを12に
  }

public void adjustmentValueChanged(AdjustmentEvent e) {//スクロールバーの値
  Scrollbar sbar = (Scrollbar)e.getAdjustable();
  if (sbar == sbar1) 
    futosa = sbar1.getValue(); //線の太さを変える
  if (sbar == sbar2 || sbar == sbar3 ||sbar == sbar4) {
    red   = sbar2.getValue();  //色の値を変える
    green = sbar3.getValue();
    blue  = sbar4.getValue();
  }
  repaint();
}

/**
 * Extended Canvas class for DrawingApli
 * [各モードにおける処理内容]
 * 1: free hand 
 *      pressed -> set x, y,  dragged  -> drawline & call repaint()
 * 2: draw line 
 *      pressed -> set x, y,  released -> drawline & call repaint()
 * 3: rect
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 * 4: circle
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 */
class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
  // ■ フィールド変数
  int x, y,z,a,b,a1,b1,x1,y1;   // mouse pointer position
  int px, py,px1,py1; // preliminary position
  int ow, oh; // width and height of the object
  int mode;   // drawing mode associated as below
  Image img = null;   // 仮の画用紙
  Graphics gc = null; // 仮の画用紙用のペン
  Dimension d; // キャンバスの大きさ取得用
  BasicStroke stroke;

  // ■ コンストラクタ
  MyCanvas(DrawingApli00 obj){
    mode=0;                       // initial value 
    this.setSize(1500,900);        // キャンバスのサイズを指定
    addMouseListener(this);       // マウスのボタンクリックなどを監視するよう指定
    addMouseMotionListener(this); // マウスの動きを監視するよう指定
  }

  // ■ メソッド（オーバーライド）
  // フレームに何らかの更新が行われた時の処理
  public void update(Graphics g) {
    paint(g); // 下記の paint を呼び出す
  }

  // ■ メソッド（オーバーライド）
  public void paint(Graphics g) {
    Graphics2D g2c= (Graphics2D) gc;
    d = getSize();   // キャンバスのサイズを取得
    if (img == null) // もし仮の画用紙の実体がまだ存在しなければ
      img = createImage(d.width, d.height); // 作成
    if (gc == null)  // もし仮の画用紙用のペン (GC) がまだ存在しなければ
      gc = img.getGraphics(); // 作成
      gc.setColor(new Color(red, green, blue)); // ペンの色設定
      g2c.setStroke(new BasicStroke(futosa));//ペンの太さを変える

    switch (mode){
    case 1: // モードが１の場合
      g2c.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 2: // モードが２の場合
      g2c.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 3: // モードが３の場合
      g2c.drawRect(px, py, ow, oh); // 仮の画用紙に描画
      break;
    case 4: // モードが４の場合
      g2c.drawOval(px, py, ow, oh); // 仮の画用紙に描画
      break;
    case 5://モードが５の場合
      g2c.drawRect(px,py,ow,oh); //仮の画用紙に描写
      g2c.fillRect(px,py,ow,oh);
      break;
    case 6://モードが6の場合
      g2c.drawOval(px, py, ow, oh);//仮の画用紙に描写
      g2c.fillOval(px, py, ow, oh);
      break;
    case 7: //モードが７の場合
      g2c.clearRect(0,0,1500,900);//仮の画用紙に描写
      break;
    case 8://モードが８の場合
      g2c.setColor(Color.white);
      g2c.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 9:
      g2c.drawLine(px, (py+y)/2, (x+px)/2, py); // 仮の画用紙に描画
      g2c.drawLine((px+x)/2, py, x, (py+y)/2);
      g2c.drawLine(x, (py+y)/2, (px+x)/2, y);
      g2c.drawLine((px+x)/2, y, px, (py+y)/2);
      break;
    case 10:
      g2c.drawLine(px, y, (px+x)/2, py);//仮の画用紙に描写
      g2c.drawLine((px+x)/2, py, x, y);
      g2c.drawLine(x, y, px, y);
      break;
    case 11:
      drawCurve(gc,a1,b1,px1,py1,x1,y1);
     case 12:
      a1=a;
      b1=b;
      g2c.drawLine(a, b, a, b); // 仮の画用紙に描画
      break;
    }
    g.drawImage(img, 0, 0, this); // 仮の画用紙の内容を MyCanvas に描画
  }

  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseClicked(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseEntered(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseExited(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー
  public void mousePressed(MouseEvent e){ // マウスボタンが押された時の処理
    switch (mode){
    case 1: // mode が１、８，１２の場合，次の内容を実行する
    case 8:
     x = e.getX();
     y = e.getY();
     break;
    case 12:
      x = e.getX();
      y = e.getY();
      a = e.getX();
      b = e.getY();
      break;
    case 2: // mode が２~11の場合，次の内容を実行する
    case 3:
    case 4: 
    case 5:
    case 6:
    case 7:
    case 9:
    case 10:
      px = e.getX();
      py = e.getY();
    case 11:
      px = e.getX();
      py = e.getY();
      px1=px;
      py1=py;
    }
  }
  public void mouseReleased(MouseEvent e){ // マウスボタンが離された時の処理
    switch (mode){
    case 2://2の場合，次の内容を実行する
     x = e.getX();
     y = e.getY();
     repaint();
     break;
    case 3: // 3~11の場合，次の内容を実行する
    case 4: 
    case 5:
    case 6:
    case 7:
    case 9:
    case 10:
    case 11:
      x = e.getX();
      y = e.getY();
      x1 = x;
      y1 = y;
      if(py < y && px<x){//左上から右下
        ow = x-px;
        oh = y-py;  
        repaint(); // 再描画
      }
      else if(py > y && px<x){//左下から右下
        z = py;
        py= y;
        y = z;
        ow = x-px;
        oh = y-py;
        repaint(); // 再描画
      }
      else if(py < y && px>x){//右上から左下
        z = px;
        px = x;
        x = z;
        ow = x-px;
        oh = y-py;
        repaint(); // 再描画
      }
      else if(py > y && px>x){///右下から左上
        z = px;
        px = x;
        x = z;
        z = py;
        py= y;
        y = z;
        ow = x-px;
        oh = y-py;
        repaint(); // 再描画
      }
      case 12://12の場合，次の内容を実行する
      repaint();

    }
  }

  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseMotionListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseDragged(MouseEvent e){ // マウスがドラッグされた時の処理
    switch (mode){
    case 1: // mode が１，８の場合，次の内容を実行する
    case 8:
      px = x;
      py = y;
      x = e.getX();
      y = e.getY();
  
      repaint(); // 再描画
    }
  }
  public void mouseMoved(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー

}

public void drawCurve(Graphics gc, int a1, int b1, int px1, int py1, int x1, int y1){//曲線を描画するメソッド
  Graphics2D g2c= (Graphics2D) gc;
  Path2D.Double p = new Path2D.Double();
  p.moveTo(px1, py1);
  p.quadTo(a1, b1, x1 , y1);
  g2c.draw(p);
  }
}