import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;

class MazingerPanel extends JPanel {

  public StatusBar statusBar;
  BufferedImage image;

  public MazingerPanel(StatusBar statusBar) {
    this.statusBar = statusBar;

    try {
      // 이미지 파일 로드
      image = ImageIO.read(new File("ma1.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int clickedX = e.getX();
        int clickedY = e.getY();
        clickStatusBar(clickedX, clickedY);
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        int currentX = e.getX();
        int currentY = e.getY();
        updateStatusBar(currentX, currentY);
      }
    });
  }

  private void updateStatusBar(int x, int y) {
    statusBar.setStatus("(" + x + ", " + y + ")");
  }

  private void clickStatusBar(int x, int y) {
    statusBar.setStatus1("(" + x + ", " + y + ")");
  }

  public void starDraw1(Graphics g, int x, int y, int z) {
    int a = z * 20;
    g.setColor(Color.white);
    Polygon polygon = new Polygon(
        new int[] { x, x - z, x - z - a, x - z, x, x + z, x + z + a, x + z },
        new int[] { y, y + a, y + a + z, y + a + z + z, y + a + z + z + a, y + a + z + z, y + a + z, y + a },
        8);
    g.fillPolygon(polygon);
  }

  // 삼각함수를 사용하여 별 모양을 그리는 메서드
  private void drawStar(Graphics2D g2d, int x, int y, int size, int numPoints) {
    double angle = 2 * Math.PI / numPoints;
    double innerSize = size / 2.5; // 내부 꼭짓점과 중심 사이의 거리

    Path2D path = new Path2D.Double();

    for (int i = 0; i < numPoints * 2; i++) {
      double radius = (i % 2 == 0) ? size : innerSize;
      double xPos = x + radius * Math.cos(i * angle);
      double yPos = y + radius * Math.sin(i * angle);

      if (i == 0) {
        path.moveTo(xPos, yPos);
      } else {
        path.lineTo(xPos, yPos);
      }
    }

    path.closePath();

    g2d.fill(path);
  }

  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    if (image != null) {
      // g.drawImage(image, 0, 0, this);
    }

    setBackground(Color.GREEN);

    Stroke newStroke;
    QuadCurve2D curve;
    QuadCurve2D curve1;
    GeneralPath path;
    Graphics2D g2d = (Graphics2D) g;
    Stroke oldStroke = g2d.getStroke();

    GradientPaint gradient = new GradientPaint(
        325, 0, new Color(58, 87, 100),
        325, 815, new Color(115, 218, 226));
    g2d.setPaint(gradient);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    starDraw1(g, 560, 50, 2);
    starDraw1(g, 74, 580, 3);
    starDraw1(g, 50, 110, 2);
    starDraw1(g, 83, 176, 1);
    starDraw1(g, 545, 471, 1);
    starDraw1(g, 389, 40, 1);
    starDraw1(g, 540, 640, 2);
    drawStar(g2d, 219, 70, 18, 5); // 5각 별 그리기
    drawStar(g2d, 599, 128, 10, 5);
    drawStar(g2d, 576, 643, 25, 5);
    drawStar(g2d, 104, 524, 15, 5);
    drawStar(g2d, 96, 350, 10, 5);
    g2d.drawOval(542, 188, 20, 20);
    g2d.drawOval(550, 194, 30, 30);
    g2d.drawOval(42, 467, 30, 30);
    g2d.drawOval(60, 486, 15, 15);
    g2d.drawOval(375, 16, 40, 40);
    g2d.drawOval(559, 588, 40, 40);

    g.setColor(Color.BLACK);
    // 머리장식 배경 채우기
    g.drawLine(289, 401, 278, 333);
    g.drawLine(263, 313, 278, 333);
    g.drawLine(263, 313, 282, 330);

    g.drawLine(338, 400, 341, 328);

    curve = new QuadCurve2D.Float(282, 330, 305, 362, 341, 328);
    g2d.draw(curve);

    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(338, 400);
    path.lineTo(289, 401);
    path.lineTo(278, 333);
    path.lineTo(262, 313);

    g2d.setColor(Color.BLACK);
    g2d.fill(path);

    g.setColor(new Color(97, 91, 100)); // 그레이
    g.fillPolygon(new int[] { 289, 301, 307, 294 }, new int[] { 340, 346, 395, 379 }, 4);
    g.setColor(new Color(221, 233, 223)); // 화이트
    g.fillPolygon(new int[] { 293, 297, 303, 298 }, new int[] { 343, 344, 395, 379 }, 4);

    g.setColor(new Color(86, 91, 114));// 어어두운 하늘
    g.fillPolygon(new int[] { 329, 302, 278, 351 }, new int[] { 476, 477, 491, 487 }, 4);// 콧구멍
    g.fillPolygon(new int[] { 336, 359, 353, 329 }, new int[] { 403, 486, 485, 473 }, 4);// 코른쪽칠
    g.fillPolygon(new int[] { 378, 386, 420, 435, 431 }, new int[] { 480, 413, 383, 384, 414 }, 5);// 오른눈밑 오른칠

    g.setColor(new Color(118, 121, 145));// 반사광
    g.fillPolygon(new int[] { 404, 431, 435, 426 }, new int[] { 447, 414, 384, 383 }, 4);// 오른눈밑 오른칠 반사광
    g.fillPolygon(new int[] { 336, 359, 355, 336 }, new int[] { 403, 486, 487, 413 }, 4);// 코오른쪽 반사광

    g.setColor(new Color(49, 138, 200));// 어두운하늘
    g.fillPolygon(new int[] { 246, 289, 270, 267 }, new int[] { 414, 403, 492, 492 }, 4);// 왼눈밑 오른칠
    g.fillPolygon(new int[] { 336, 359, 373, 378, 383 }, new int[] { 403, 486, 486, 480, 411 }, 5);// 오른눈밑 왼칠

    g.setColor(new Color(80, 178, 207));// 하늘
    g.fillPolygon(new int[] { 289, 336, 329, 302 }, new int[] { 404, 403, 476, 477 }, 4);// 코칠
    g.fillPolygon(new int[] { 329, 330, 355, 351 }, new int[] { 476, 473, 485, 487 }, 4);// 코오른쪽명암
    g.fillPolygon(new int[] { 336, 383, 413, 420, 386, 378, 373, 382 },
        new int[] { 403, 407, 382, 383, 413, 480, 485, 413 }, 8);// 오른눈밑 왼칠 명암

    g.setColor(new Color(125, 216, 234));// 연하늘
    g.fillPolygon(new int[] { 289, 270, 302 }, new int[] { 404, 492, 474 }, 3);// 코왼쪽칠
    g.fillPolygon(new int[] { 209, 246, 267, 202, 197 }, new int[] { 392, 414, 492, 423, 395 }, 5); // 왼눈밑 왼칠

    g.setColor(new Color(74, 72, 85));// 어어어어어두운
    g.fillPolygon(new int[] { 329, 334, 351 }, new int[] { 476, 487, 487 }, 3);// 콧구멍명암

    // 눈 칠
    g.setColor(new Color(240, 222, 0));// 노란색
    g.fillPolygon(new int[] { 287, 243, 209, 245 }, new int[] { 403, 387, 392, 414 }, 4); // 왼 눈
    g.fillPolygon(new int[] { 338, 379, 413, 383 }, new int[] { 401, 379, 382, 407 }, 4); // 오른 눈
    // 명암
    g.setColor(new Color(215, 179, 49));// 어두운 노랑
    g.fillPolygon(new int[] { 287, 243, 250 }, new int[] { 403, 387, 411 }, 3); // 왼 눈
    g.setColor(new Color(220, 138, 56));// 주황
    g.fillPolygon(new int[] { 383, 413, 379 }, new int[] { 380, 382, 407 }, 3); // 오른 눈
    g.fillPolygon(new int[] { 287, 254, 260 }, new int[] { 403, 392, 410 }, 3); // 왼 눈
    g.setColor(new Color(158, 87, 44));// 어두운주황
    g.fillPolygon(new int[] { 384, 402, 383 }, new int[] { 384, 385, 401 }, 3); // 오른 눈
    g.setColor(new Color(230, 217, 141));// 밝은 노랑
    newStroke = new BasicStroke(2f);
    g2d.setStroke(newStroke);
    g.drawLine(389, 386, 388, 393);// 오른눈
    g.drawLine(395, 387, 394, 391);// 오른눈
    g.setColor(new Color(255, 250, 203));// 흰색
    newStroke = new BasicStroke(4f);
    g2d.setStroke(newStroke);
    g.drawLine(236, 394, 241, 410);// 왼눈
    g.drawLine(226, 397, 237, 404);// 왼눈
    g2d.setStroke(oldStroke);
    g.fillPolygon(new int[] { 355, 375, 377 }, new int[] { 400, 400, 388 }, 3);// 오른눈
    g.setColor(new Color(240, 222, 0));// 노란색
    g.fillPolygon(new int[] { 248, 253, 255, 251 }, new int[] { 395, 396, 405, 406 }, 4); // 왼 눈

    g.setColor(new Color(202, 75, 45)); // 빨강
    g.fillPolygon(new int[] { 295, 284, 315, 335, 334 }, new int[] { 401, 356, 400, 364, 399 }, 5); // 이마장식 붉은 부분

    g.setColor(new Color(149, 83, 72)); // 어두운 빨강
    g.fillPolygon(new int[] { 325, 335, 334 }, new int[] { 399, 364, 399 }, 3); // 이마장식 붉은 부분 명암

    g.setColor(new Color(221, 233, 223)); // 흰색
    g.fillPolygon(new int[] { 329, 330, 301, 302 }, new int[] { 476, 473, 474, 477 }, 4);
    g.fillPolygon(new int[] { 289, 302, 305, 293 }, new int[] { 404, 477, 476, 404 }, 4);// 코명암
    g.fillPolygon(new int[] { 302, 270, 278, 302 }, new int[] { 474, 492, 491, 477 }, 4);// 코왼명암
    g.fillPolygon(new int[] { 336, 338, 371 }, new int[] { 403, 406, 411 }, 3);// 오른눈밑 왼칠 명암(밝은)
    g.fillPolygon(new int[] { 208, 245, 289, 248, 266, 243 }, new int[] { 394, 415, 405, 420, 492, 420 }, 6);// 왼눈밑

    // 명암(밝은)
    g.fillPolygon(new int[] { 299, 301, 300, 297 }, new int[] { 396, 395, 378, 376 }, 4);
    curve = new QuadCurve2D.Float(283, 330, 305, 364, 341, 328);
    g2d.draw(curve);

    // 왼뿔
    // 왼뿔채우기
    g.setColor(new Color(225, 231, 222));
    g.fillPolygon(new int[] { 159, 120, 186, 243, 290, 278, 214 }, new int[] { 63, 267, 394, 386, 401, 333, 247 }, 7);
    g.setColor(new Color(209, 217, 206));
    g.fillPolygon(new int[] { 159, 167, 243, 290, 278, 214 }, new int[] { 63, 255, 386, 401, 333, 247 }, 6);

    // 왼뿔 위
    curve = new QuadCurve2D.Float(167, 255, 143, 257, 120, 267);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(159, 63);
    g.setColor(new Color(242, 244, 240));
    g2d.fill(path);

    // 왼뿔 명암
    g.setColor(new Color(255, 255, 255));
    g.fillPolygon(new int[] { 159, 167, 161, 157 }, new int[] { 63, 255, 257, 77 }, 4);
    g.fillPolygon(new int[] { 242, 167, 161, 236 }, new int[] { 386, 255, 257, 386 }, 4);

    // 흰색 곡선
    newStroke = new BasicStroke(5.0f); // 5.0f는 두께를 나타냄
    g2d.setStroke(newStroke);
    curve = new QuadCurve2D.Float(124, 270, 143, 258, 166, 259);
    g2d.draw(curve);
    g2d.setStroke(oldStroke);
    // 왼쪽뿔 커브
    g2d.setColor(Color.BLACK);
    curve = new QuadCurve2D.Float(167, 255, 143, 257, 120, 267);
    g2d.draw(curve);

    // 왼쪽 뿔 명암
    g2d.setColor(new Color(177, 178, 185));
    g.fillPolygon(new int[] { 290, 278, 214 }, new int[] { 401, 333, 247 }, 3);

    curve = new QuadCurve2D.Float(274, 359, 273, 380, 261, 392);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(290, 401);
    g2d.fill(path);

    // 오른 뿔 채우기
    g.setColor(new Color(237, 237, 237));
    g.fillPolygon(new int[] { 447, 397, 341, 339, 382, 447 }, new int[] { 54, 238, 328, 393, 371, 242 }, 6);

    // 오른뿔 빛받는
    g2d.setColor(Color.white);
    g.fillPolygon(new int[] { 397, 341, 388 }, new int[] { 238, 328, 284 }, 3);

    // 오른뿔 아래 채우기
    curve = new QuadCurve2D.Float(447, 242, 477, 243, 504, 255);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(448, 382);
    path.lineTo(382, 371);
    g2d.setColor(new Color(144, 160, 158));
    g2d.fill(path);

    // 오른뿔 아래 반사광
    g2d.setColor(new Color(158, 173, 172));
    g.fillPolygon(new int[] { 448, 504, 487, 425 }, new int[] { 382, 255, 248, 377 }, 4);
    g2d.setColor(new Color(188, 194, 205));
    g.fillPolygon(new int[] { 448, 504, 496, 437 }, new int[] { 382, 255, 251, 380 }, 4);

    // 오른뿔 아래 명암
    g.setColor(new Color(95, 119, 119));
    g.fillPolygon(new int[] { 382, 410, 388, 415 }, new int[] { 371, 375, 368, 307 }, 4);

    // 오른뿔 위 채우기
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(447, 54);
    g2d.setColor(new Color(95, 119, 119));
    g2d.fill(path);

    // 오른뿔 위 명암
    g.setColor(new Color(115, 136, 136));
    g.fillPolygon(new int[] { 487, 504, 447 }, new int[] { 248, 255, 54 }, 3);

    g2d.setColor(Color.BLACK);
    g2d.draw(curve); // 오른뿔 커브

    // 오른뿔 제일 아래 명암
    g.setColor(new Color(209, 217, 206));
    g.fillPolygon(new int[] { 339, 338, 380, 448, 383 }, new int[] { 391, 400, 379, 382, 371 }, 5);

    // 마스크 오른쪽 색칠
    g2d.setColor(new Color(95, 119, 119)); // 어두운
    g.fillPolygon(new int[] { 365, 440, 447, 435, 431, 372 }, new int[] { 569, 450, 386, 386, 414, 492 }, 6);
    g.setColor(new Color(115, 136, 136)); // 좀 더 밝은
    g.fillPolygon(new int[] { 440, 447, 435, 431, 419, 414 }, new int[] { 450, 386, 385, 413, 428, 492 }, 6);
    g2d.setColor(new Color(144, 160, 158)); // 좀 더더 밝은
    g.fillPolygon(new int[] { 440, 447, 441, 435 }, new int[] { 450, 386, 383, 457 }, 4);

    // 마스크 왼쪽 색칠
    g2d.setColor(new Color(205, 230, 224));
    g.fillPolygon(new int[] { 277, 267, 202, 197, 187, 197 }, new int[] { 574, 492, 422, 393, 395, 458 }, 6);

    // 마스크 가운데 색칠
    g2d.setColor(new Color(205, 230, 224));
    g.fillPolygon(new int[] { 267, 277, 365, 368 }, new int[] { 497, 574, 569, 494 }, 4);
    g.setColor(new Color(107, 132, 163)); // 명암
    g.fillPolygon(new int[] { 299, 366, 368 }, new int[] { 496, 544, 494 }, 3);
    g.fillPolygon(new int[] { 267, 267, 369, 374 }, new int[] { 491, 498, 494, 486 }, 4);
    g.setColor(new Color(132, 156, 180)); // 명암
    g.fillPolygon(new int[] { 299, 332, 367, 368 }, new int[] { 496, 520, 514, 494 }, 4);
    g.setColor(new Color(176, 207, 196)); // 밑명암
    g.fillPolygon(new int[] { 277, 365, 366, 275 }, new int[] { 574, 569, 552, 556 }, 4);

    g.setColor(Color.white);// 흰색
    g.fillPolygon(new int[] { 365, 368, 267, 267, 372, 369 }, new int[] { 569, 494, 497, 494, 492, 561 }, 6);

    newStroke = new BasicStroke(2.5f); // 5.0f는 두께를 나타냄
    g2d.setStroke(newStroke);
    g.drawLine(370, 492, 432, 415);
    g.drawLine(436, 385, 432, 415);
    g.drawLine(371, 493, 388, 469);
    newStroke = new BasicStroke(4f);
    g2d.setStroke(newStroke);
    g.drawLine(267, 495, 200, 425);
    g.drawLine(195, 392, 200, 425);
    g.drawLine(264, 495, 275, 568);

    // 마스크 네모 밖 반사광 (왼쪽)
    newStroke = new BasicStroke(3f);
    g2d.setStroke(newStroke);
    g.drawLine(205, 443, 207, 465);
    g.drawLine(212, 471, 207, 465);
    g.drawLine(219, 459, 220, 484);
    g.drawLine(225, 492, 220, 484);
    g.drawLine(231, 474, 234, 505);
    g.drawLine(240, 512, 234, 505);
    g.drawLine(245, 491, 249, 524);
    g.drawLine(256, 532, 249, 524);

    // 마스크 네모 밖 반사광
    newStroke = new BasicStroke(6f);
    g2d.setStroke(newStroke);
    g.drawPolygon(new int[] { 274, 291, 294, 282 }, new int[] { 503, 502, 561, 561 }, 4); // 왼쪽
    g.drawPolygon(new int[] { 308, 327, 326, 312 }, new int[] { 506, 519, 559, 560 }, 4); // 가운데
    g.drawPolygon(new int[] { 340, 359, 357, 343 }, new int[] { 529, 542, 557, 558 }, 4); // 오른쪽

    g2d.setStroke(oldStroke);

    // 마스크 네모 채우기

    g.drawLine(309, 506, 326, 506);

    g.setColor(new Color(131, 143, 129));
    g.fillPolygon(new int[] { 274, 291, 294, 282 }, new int[] { 503, 502, 561, 561 }, 4); // 왼쪽
    g.fillPolygon(new int[] { 308, 325, 326, 312 }, new int[] { 501, 501, 559, 560 }, 4); // 가운데
    g.fillPolygon(new int[] { 340, 358, 357, 343 }, new int[] { 500, 499, 557, 558 }, 4); // 오른쪽

    g.setColor(new Color(187, 205, 195)); // 밝은 명암
    g.fillRect(310, 510, 13, 14);
    g.fillRect(311, 536, 11, 8);
    g.fillRect(278, 518, 13, 14);
    g.fillRect(280, 546, 11, 5);
    g.fillRect(343, 538, 11, 16);

    g.setColor(new Color(34, 37, 32)); // 어두운 명암
    g.fillPolygon(new int[] { 307, 325, 326 }, new int[] { 501, 501, 515 }, 3);
    g.fillPolygon(new int[] { 340, 358, 357, 341 }, new int[] { 500, 499, 538, 526 }, 4);

    // 마스크 왼쪽 네모네모
    g.setColor(new Color(177, 173, 178));
    g.fillPolygon(new int[] { 207, 212, 214, 209 }, new int[] { 443, 449, 471, 465 }, 4);
    g.fillPolygon(new int[] { 221, 225, 227, 222 }, new int[] { 459, 466, 492, 484 }, 4);
    g.setColor(new Color(158, 157, 158));
    g.fillPolygon(new int[] { 233, 237, 242, 236 }, new int[] { 474, 478, 512, 505 }, 4);
    g.setColor(new Color(126, 124, 127));
    g.fillPolygon(new int[] { 247, 253, 258, 251 }, new int[] { 491, 498, 532, 524 }, 4);

    // 마스크 오른쪽 네모네모
    g.setColor(new Color(34, 37, 32));
    g.fillPolygon(new int[] { 380, 386, 384, 378 }, new int[] { 492, 485, 522, 529 }, 4);
    g.fillPolygon(new int[] { 395, 402, 401, 395 }, new int[] { 474, 463, 493, 500 }, 4);
    g.fillPolygon(new int[] { 411, 416, 416, 412 }, new int[] { 454, 445, 468, 472 }, 4);
    g.fillPolygon(new int[] { 424, 429, 429, 425 }, new int[] { 435, 429, 451, 456 }, 4);

    g.setColor(Color.BLACK);
    // 가운데
    g.drawLine(309, 507, 325, 507);
    g.drawLine(309, 513, 325, 513);
    g.drawLine(309, 520, 325, 520);
    g.drawLine(310, 527, 325, 527);
    g.drawLine(310, 533, 325, 533);
    g.drawLine(311, 540, 325, 540);
    g.drawLine(311, 547, 325, 547);
    g.drawLine(312, 553, 325, 553);
    // 오른쪽
    g.drawLine(341, 505, 358, 505);
    g.drawLine(341, 511, 358, 511);
    g.drawLine(341, 517, 358, 517);
    g.drawLine(341, 523, 357, 523);
    g.drawLine(341, 529, 357, 529);
    g.drawLine(341, 535, 357, 535);
    g.drawLine(342, 541, 357, 541);
    g.drawLine(342, 547, 357, 547);
    g.drawLine(342, 552, 357, 552);
    // 왼쪽
    g.drawLine(275, 508, 291, 508);
    g.drawLine(275, 514, 291, 514);
    g.drawLine(276, 521, 291, 521);
    g.drawLine(277, 528, 291, 528);
    g.drawLine(278, 534, 292, 534);
    g.drawLine(279, 541, 292, 541);
    g.drawLine(280, 548, 292, 548);
    g.drawLine(280, 554, 293, 554);

    ////////////////////////
    ////////////////////////

    g.setColor(Color.BLACK);

    g.drawLine(267, 492, 278, 491);
    g.drawLine(281, 489, 373, 486);// 마스크 위

    g.drawLine(278, 491, 302, 477);
    g.drawLine(329, 476, 302, 477);
    g.drawLine(329, 476, 351, 487); // 콧구멍

    g.drawPolygon(new int[] { 289, 336, 329, 302 }, new int[] { 404, 403, 476, 477 }, 4);// 코
    g.drawLine(289, 404, 270, 492); // 코왼쪽
    g.drawLine(336, 403, 359, 486); // 코오른

    g.drawLine(373, 486, 431, 414);// 오른눈밑 밑 /
    g.drawLine(386, 413, 378, 480);// 오른눈밑 왼쪽 |
    g.drawLine(386, 413, 412, 390);// 오른눈밑 위 /
    g.drawLine(435, 385, 431, 414);// 오른눈밑 오른 |

    g.drawLine(202, 423, 267, 492);// 왼눈밑 밑 \
    g.drawLine(197, 395, 202, 423);// 왼눈밑 왼쪽 |
    g.drawLine(248, 420, 267, 492);// 왼눈밑 오른 |

    newStroke = new BasicStroke(5.0f); // 5.0f는 두께를 나타냄
    g2d.setStroke(newStroke);

    g.drawLine(287, 403, 338, 401); // 눈 중간

    g2d.setColor(new Color(49, 52, 45));
    g.drawPolygon(new int[] { 287, 243, 209, 245 }, new int[] { 403, 387, 392, 414 }, 4); // 왼 눈
    g.setColor(Color.BLACK);
    g.drawPolygon(new int[] { 338, 379, 413, 383 }, new int[] { 401, 379, 382, 407 }, 4); // 오른 눈

    newStroke = new BasicStroke(3.0f);
    g.drawLine(413, 382, 446, 384);// 오른눈 아라
    g2d.setColor(new Color(69, 74, 57));
    g.drawLine(207, 392, 188, 394);// 왼눈 아라
    g.setColor(Color.BLACK);

    g2d.setStroke(oldStroke);

    g.drawPolygon(new int[] { 295, 284, 315, 335, 334 }, new int[] { 401, 356, 400, 364, 399 }, 5); // 이마장식 붉은 부분

    newStroke = new BasicStroke(2.0f);
    g2d.setStroke(newStroke);

    // 이마장식 노란
    curve = new QuadCurve2D.Float(288, 349, 314, 360, 336, 344);
    g2d.draw(curve);

    g2d.drawLine(314, 388, 288, 349);
    g2d.drawLine(314, 388, 336, 344);

    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(314, 388);

    g2d.setColor(new Color(229, 212, 11));
    g2d.fill(path);

    g.setColor(new Color(221, 233, 223));
    curve = new QuadCurve2D.Float(297, 353, 314, 356, 322, 353);
    g2d.draw(curve);

    g2d.setStroke(oldStroke);

    g.setColor(new Color(228, 153, 26));
    g.fillPolygon(new int[] { 336, 325, 314 }, new int[] { 344, 350, 388 }, 3); // 이마장식 노란색 명암
    g.setColor(Color.white); // 흰색
    g.drawLine(289, 405, 335, 404);

    //

    // 가운데 뿔 칠하기
    curve = new QuadCurve2D.Float(258, 216, 288, 181, 301, 36);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(307, 50);
    path.lineTo(308, 292);
    path.lineTo(268, 281);
    g.setColor(new Color(236, 82, 76));
    g2d.fill(path);

    curve1 = new QuadCurve2D.Float(355, 212, 315, 152, 306, 49);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(304, 176);
    g.setColor(new Color(185, 88, 76));
    g2d.fill(path);

    // 가운데 뿔 명암
    g.setColor(new Color(221, 233, 223));// 흰색
    g.fillPolygon(new int[] { 292, 301, 303, 304 }, new int[] { 109, 36, 43, 135 }, 4);
    g.fillPolygon(new int[] { 303, 300, 301, 304 }, new int[] { 43, 36, 181, 176 }, 4);
    g.fillPolygon(new int[] { 284, 296, 293 }, new int[] { 160, 109, 104 }, 3);

    // 가운데 선 그리기
    g.setColor(Color.BLACK);
    g2d.draw(curve);
    g2d.draw(curve1);

    // 가운데 뿔 명암 커브
    g.setColor(new Color(221, 233, 223));// 흰색
    curve = new QuadCurve2D.Float(304, 170, 287, 190, 279, 238);
    g2d.fill(curve);
    curve = new QuadCurve2D.Float(259, 217, 289, 182, 302, 37);
    g2d.draw(curve);

    // 가운데 뿔 칠하기
    // 타원 왼오
    g.setColor(new Color(239, 182, 172));
    curve = new QuadCurve2D.Float(282, 330, 266, 303, 268, 281);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(310, 270);
    path.lineTo(310, 324);
    g2d.fill(path);

    curve1 = new QuadCurve2D.Float(341, 328, 354, 307, 351, 279);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(310, 270);
    path.lineTo(310, 324);
    g.setColor(new Color(202, 75, 45)); // 빨강
    g2d.fill(path);

    // 타원 아래
    curve = new QuadCurve2D.Float(282, 330, 305, 362, 341, 328);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(310, 274);
    g.setColor(new Color(218, 122, 71));
    g2d.fill(path);

    // 명암 밝은 주황
    g.setColor(new Color(239, 182, 172));
    curve = new QuadCurve2D.Float(281, 328, 289, 344, 311, 345);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(311, 288);
    g2d.fill(path);

    // 튀어나온 주황색 칠
    g.fillPolygon(new int[] { 273, 268, 279 }, new int[] { 281, 281, 236 }, 3);
    // 흰색 명암
    g.setColor(Color.white);
    curve = new QuadCurve2D.Float(279, 236, 272, 259, 268, 292);
    g2d.draw(curve);

    // 아래 커브 다시 그리기
    g.setColor(Color.BLACK);
    curve = new QuadCurve2D.Float(282, 330, 305, 362, 341, 328);
    g2d.draw(curve);

    // 가운데 뿔 안쪽 칠하기
    curve = new QuadCurve2D.Float(273, 277, 280, 212, 304, 176);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(318, 266);
    g.setColor(new Color(218, 122, 71));
    g2d.fill(path);

    curve1 = new QuadCurve2D.Float(351, 279, 340, 220, 304, 176);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(311, 326);
    g2d.fill(path);

    // 가운데뿔 오른쪽 명암
    curve1 = new QuadCurve2D.Float(351, 279, 340, 220, 304, 176);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(355, 212);
    g.setColor(new Color(185, 88, 76));
    g2d.fill(path);

    g.setColor(new Color(109, 90, 83));
    g.fillPolygon(new int[] { 304, 315, 304 }, new int[] { 140, 183, 150 }, 3);
    g.fillPolygon(new int[] { 351, 344, 353 }, new int[] { 276, 252, 234 }, 3);
    curve1 = new QuadCurve2D.Float(304, 150, 321, 186, 331, 215);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(304, 176);
    g2d.fill(path);

    // 가운데뿔 안쪽 명암
    // 어두운
    curve1 = new QuadCurve2D.Float(301, 180, 285, 222, 292, 230);
    path = new GeneralPath();
    path.append(curve1, false);
    path.lineTo(280, 236);
    path.lineTo(286, 214);
    g2d.fill(path);
    g.fillPolygon(new int[] { 292, 273, 280 }, new int[] { 230, 276, 236 }, 3);
    g.fillPolygon(new int[] { 344, 351, 346, 341 }, new int[] { 251, 279, 285, 257 }, 4);
    // 주황
    g.setColor(new Color(218, 122, 71));
    newStroke = new BasicStroke(3.0f); // 5.0f는 두께를 나타냄
    g2d.setStroke(newStroke);
    curve = new QuadCurve2D.Float(293, 226, 280, 242, 281, 254);
    g2d.draw(curve);
    g2d.setStroke(oldStroke);
    // 밝은
    g.setColor(new Color(239, 182, 172));
    curve1 = new QuadCurve2D.Float(304, 176, 305, 213, 318, 224);
    g2d.fill(curve1);
    curve1 = new QuadCurve2D.Float(330, 216, 328, 231, 318, 224);
    g2d.fill(curve1);
    g.fillPolygon(new int[] { 304, 318, 330 }, new int[] { 176, 224, 216 }, 3);

    // 가운데뿔 안쪽 그리기
    g.setColor(Color.BLACK);
    curve = new QuadCurve2D.Float(273, 277, 280, 212, 304, 176);
    curve1 = new QuadCurve2D.Float(351, 279, 340, 220, 304, 176);
    g2d.draw(curve);
    g2d.draw(curve1);

    /////////////////////////////////////////////////
    /////////////////////////////////////////////////

    // 아래 타원
    Ellipse2D ellipse = new Ellipse2D.Float(275, 222, 345 - 275, 341 - 222);
    g.setColor(new Color(21, 22, 20)); // 안쪽 검은색
    g2d.fill(ellipse);
    g.setColor(Color.BLACK);
    g2d.draw(ellipse);

    ellipse = new Ellipse2D.Float(273, 236, 347 - 273, 341 - 236);
    g.setColor(new Color(105, 188, 221)); // 하늘색
    g2d.fill(ellipse);

    // 하늘색
    g.setColor(new Color(159, 233, 238));
    curve = new QuadCurve2D.Float(302, 252, 287, 266, 286, 284);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(275, 280);
    path.lineTo(295, 242);
    g2d.fill(path);
    g.fillPolygon(new int[] { 279, 295, 299, 313, 320, 322, 305, 295, 285 },
        new int[] { 307, 296, 309, 325, 324, 336, 338, 334, 323 }, 9);

    // 파란색
    g.setColor(new Color(104, 148, 221));
    Ellipse2D ellipse1 = new Ellipse2D.Float(297, 239, 25, 20);
    g2d.fill(ellipse1);
    g.fillPolygon(new int[] { 319, 329, 339, 331, 317 }, new int[] { 248, 260, 259, 248, 239 }, 5);
    ellipse1 = new Ellipse2D.Float(319, 280, 15, 10);
    g2d.fill(ellipse1);
    g.fillPolygon(new int[] { 342, 335, 327, 327, 344 }, new int[] { 268, 275, 278, 288, 286 }, 5);
    curve = new QuadCurve2D.Float(327, 288, 329, 316, 315, 323);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(324, 333);
    path.lineTo(339, 283);
    g2d.fill(path);

    // 어두운 부분
    g.setColor(new Color(59, 95, 93)); // 어두운 칼라
    g.fillPolygon(new int[] { 279, 286, 291, 295, 278 }, new int[] { 277, 284, 284, 300, 305 }, 5);
    curve = new QuadCurve2D.Float(297, 291, 297, 320, 322, 321);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(312, 325);
    path.lineTo(299, 318);
    path.lineTo(291, 289);
    g2d.fill(path);

    ellipse1 = new Ellipse2D.Float(295, 271, 17, 18);
    g2d.fill(ellipse1);
    ellipse1 = new Ellipse2D.Float(312, 271, 17, 18);
    g2d.fill(ellipse1);
    g.fillPolygon(new int[] { 313, 295, 313, 330 }, new int[] { 308, 284, 278, 284 }, 4);

    g2d.setStroke(new BasicStroke(5f));
    g2d.draw(new QuadCurve2D.Float(319, 328, 349, 315, 340, 276));
    g2d.draw(new QuadCurve2D.Float(334, 282, 338, 303, 325, 324));
    g.fillPolygon(new int[] { 335, 341, 342, 327, 335 }, new int[] { 280, 274, 302, 332, 293 }, 5);
    g.fillPolygon(new int[] { 322, 320, 330 }, new int[] { 323, 332, 325 }, 3);

    g.fillPolygon(new int[] { 326, 332, 335, 336, 334, 331, 329 }, new int[] { 252, 257, 260, 265, 268, 267, 263 }, 7);

    // 하트라도 그려야겟다......
    g.setColor(new Color(223, 66, 43)); // 빨간
    ellipse1 = new Ellipse2D.Float(293, 270, 17, 18);
    g2d.fill(ellipse1);
    ellipse1 = new Ellipse2D.Float(310, 270, 17, 18);
    g2d.fill(ellipse1);
    g.fillPolygon(new int[] { 311, 293, 311, 327 }, new int[] { 305, 282, 276, 282 }, 4);

    g.setColor(new Color(255, 203, 178)); // 빨간
    ellipse1 = new Ellipse2D.Float(296, 276, 7, 8);
    g2d.fill(ellipse1);
    g.drawLine(304, 288, 307, 292);

    // 흰색 빛 받는

    g2d.setStroke(new BasicStroke(3.5f));

    g.setColor(Color.white);
    ellipse1 = new Ellipse2D.Float(276, 238, 344 - 275, 339 - 238);
    g2d.draw(ellipse1);

    g2d.fill(new Ellipse2D.Float(278, 298, 14, 12));
    g2d.fill(new Ellipse2D.Float(331, 269, 8, 8));

    g2d.draw(new QuadCurve2D.Float(322, 250, 327, 253, 330, 261));
    g2d.draw(new QuadCurve2D.Float(280, 280, 283, 250, 308, 238));
    g2d.draw(new QuadCurve2D.Float(292, 247, 310, 236, 323, 242));

    g2d.draw(new QuadCurve2D.Float(282, 271, 287, 256, 307, 239));

    g2d.setStroke(new BasicStroke(5f));
    g2d.draw(new QuadCurve2D.Float(289, 314, 299, 337, 327, 332));
    g.drawLine(325, 332, 335, 322);
    g2d.setStroke(new BasicStroke(1.5f));
    g2d.draw(new QuadCurve2D.Float(286, 294, 284, 275, 293, 262));
    g2d.draw(new QuadCurve2D.Float(286, 294, 282, 275, 293, 262));
    g2d.draw(new QuadCurve2D.Float(287, 294, 286, 275, 294, 262));

    g2d.draw(new QuadCurve2D.Float(296, 258, 305, 247, 316, 248));
    g2d.draw(new QuadCurve2D.Float(296, 258, 305, 245, 316, 248));

    g2d.draw(new QuadCurve2D.Float(281, 296, 279, 273, 288, 252));
    g2d.draw(new QuadCurve2D.Float(299, 317, 316, 337, 332, 314));
    g2d.draw(new QuadCurve2D.Float(299, 317, 316, 334, 332, 314));
    g2d.draw(new QuadCurve2D.Float(299, 317, 316, 338, 332, 314));

    g2d.draw(new QuadCurve2D.Float(294, 296, 294, 310, 303, 315));
    g2d.draw(new QuadCurve2D.Float(294, 296, 296, 310, 303, 315));
    g2d.draw(new QuadCurve2D.Float(335, 307, 337, 301, 337, 293));

    g.setColor(Color.BLACK);
    g2d.draw(ellipse);
    g2d.setStroke(oldStroke);

    // 가운데 뿔왼오 장식 칠하기 (왼쪽)
    // 회색
    g2d.setColor(new Color(148, 153, 141));
    curve = new QuadCurve2D.Float(258, 215, 244, 211, 228, 219);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(232, 244);
    path.lineTo(261, 240);
    g2d.fill(path);
    // 명암
    g.setColor(new Color(194, 206, 194));
    g.fillPolygon(new int[] { 247, 251, 253, 249 }, new int[] { 218, 219, 235, 235 }, 4);
    g.fillPolygon(new int[] { 239, 228, 228, 241 }, new int[] { 215, 219, 224, 220 }, 4);
    g.setColor(new Color(155, 188, 187));
    g.fillPolygon(new int[] { 258, 253, 256, 260 }, new int[] { 220, 219, 234, 235 }, 4);

    // 붉은 색
    g.setColor(new Color(236, 82, 76));
    curve = new QuadCurve2D.Float(232, 244, 247, 238, 262, 240);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(268, 281);
    path.lineTo(268, 296);
    path.lineTo(281, 329);
    path.lineTo(265, 316);
    path.lineTo(238, 279);
    g2d.fill(path);
    g.fillPolygon(new int[] { 234, 206, 214, 238 }, new int[] { 253, 220, 248, 279 }, 4);
    // 명암
    g.setColor(new Color(185, 88, 76));// 어두운붉은
    g.fillPolygon(new int[] { 268, 281, 265, 259, 251 }, new int[] { 296, 329, 316, 308, 276 }, 5);
    g.setColor(new Color(109, 90, 83)); // 더더어두운
    g.fillPolygon(new int[] { 214, 227, 211 }, new int[] { 248, 264, 235 }, 3);
    g.setColor(new Color(221, 233, 223));// 흰색
    curve = new QuadCurve2D.Float(232, 245, 247, 238, 261, 241);
    g2d.draw(curve);
    g.fillPolygon(new int[] { 239, 243, 246, 243 }, new int[] { 243, 242, 269, 274 }, 4);

    // 노란색
    g.setColor(new Color(240, 222, 0));
    curve = new QuadCurve2D.Float(228, 224, 204, 208, 190, 165);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(206, 220);
    path.lineTo(234, 253);
    g2d.fill(path);
    // 명암
    g.setColor(new Color(223, 175, 92));// 주황
    g.fillPolygon(new int[] { 191, 210, 221, 207 }, new int[] { 169, 207, 237, 221 }, 4);
    g.setColor(Color.white);// 흰색명암
    g.fillPolygon(new int[] { 203, 226, 222, 226 }, new int[] { 198, 224, 223, 239 }, 4);

    // 가운데 뿔왼오 장식 칠하기 (오른쪽)
    // 회색
    g2d.setColor(new Color(95, 119, 119));
    curve = new QuadCurve2D.Float(355, 212, 373, 204, 385, 213);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(384, 235);
    path.lineTo(354, 236);
    g2d.fill(path);
    // 명암
    g2d.setColor(new Color(148, 153, 141)); // 밝은회색
    g.fillPolygon(new int[] { 359, 359, 379, 379, 369 }, new int[] { 216, 233, 233, 215, 213 }, 5);
    g.fillPolygon(new int[] { 367, 362, 376, 376 }, new int[] { 208, 233, 218, 209 }, 4);
    g.setColor(new Color(221, 233, 223)); // 흰색
    g.fillPolygon(new int[] { 362, 362, 366, 366 }, new int[] { 215, 228, 228, 215 }, 4);
    g.fillPolygon(new int[] { 369, 366, 366, 369 }, new int[] { 231, 231, 215, 214 }, 4);
    g.setColor(new Color(155, 188, 187)); // 밝은회색(약간 파란)
    g.fillPolygon(new int[] { 379, 379, 385, 386 }, new int[] { 227, 215, 217, 229 }, 4);

    // 붉은색
    g.setColor(new Color(236, 82, 76));
    curve = new QuadCurve2D.Float(353, 235, 368, 230, 384, 235);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(385, 257);
    path.lineTo(347, 318);
    path.lineTo(351, 291);
    path.lineTo(351, 276);
    g2d.fill(path);
    // 명암
    // 어두운 붉은
    g.setColor(new Color(185, 88, 76)); // 어두운붉은
    g.fillPolygon(new int[] { 370, 370, 351, 351, 347, 384, 384, 374 },
        new int[] { 233, 256, 279, 300, 317, 257, 234, 233 }, 8);
    g.setColor(new Color(150, 90, 83)); // 더어두운
    g.fillPolygon(new int[] { 378, 378, 379, 384, 384 }, new int[] { 233, 256, 267, 257, 234 }, 5);
    g.fillPolygon(new int[] { 385, 384, 401, 397 }, new int[] { 257, 240, 223, 238 }, 4);
    g.setColor(new Color(109, 90, 83)); // 더더어두운
    g.fillPolygon(new int[] { 385, 384, 391, 391 }, new int[] { 257, 240, 235, 248 }, 4);
    g.setColor(new Color(221, 233, 223)); // 흰색
    curve = new QuadCurve2D.Float(353, 236, 368, 231, 384, 236);
    g2d.draw(curve);
    g.fillPolygon(new int[] { 363, 363, 364, 367, 367 }, new int[] { 234, 247, 253, 247, 234 }, 5);

    // 노란색
    g.setColor(new Color(240, 222, 0));
    curve = new QuadCurve2D.Float(385, 216, 402, 201, 414, 175);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(401, 223);
    path.lineTo(384, 240);
    g2d.fill(path);
    g2d.draw(curve);
    // 명암
    g.setColor(new Color(220, 138, 56)); // 주황
    curve = new QuadCurve2D.Float(399, 206, 402, 220, 387, 238);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(401, 223);
    path.lineTo(412, 180);
    g2d.fill(path);

    // 뿔왼오 장식과 맞닿는 커브 다시 그리기
    curve = new QuadCurve2D.Float(282, 330, 266, 303, 268, 281);
    curve1 = new QuadCurve2D.Float(341, 328, 354, 307, 351, 279);
    g.setColor(Color.BLACK);
    g2d.draw(curve);
    g2d.draw(curve1);

    // 가운데 뿔
    g.setColor(Color.BLACK);
    g.drawLine(268, 281, 258, 216);
    g.drawLine(351, 279, 355, 212);
    g.drawLine(301, 36, 306, 49);
    g.drawLine(303, 41, 304, 176);

    // 가운데 뿔 왼쪽 장식
    g.drawLine(234, 221, 237, 242);
    g.drawLine(240, 220, 243, 239);
    g.drawLine(247, 219, 249, 238);
    g.drawLine(253, 220, 256, 238);
    curve = new QuadCurve2D.Float(258, 215, 244, 211, 228, 219);
    g2d.draw(curve);
    curve = new QuadCurve2D.Float(258, 220, 244, 216, 228, 224);
    g2d.draw(curve);
    curve = new QuadCurve2D.Float(232, 244, 247, 238, 261, 240);
    g2d.draw(curve);
    g.drawLine(228, 219, 238, 279);

    // 왼뿔과 잇는
    curve = new QuadCurve2D.Float(228, 224, 204, 208, 190, 165);
    g2d.draw(curve);
    g.drawLine(234, 253, 206, 220);

    // 가운데 뿔 오른쪽 장식
    g.drawLine(359, 215, 359, 233);
    g.drawLine(365, 215, 365, 232);
    g.drawLine(372, 214, 372, 232);
    g.drawLine(379, 215, 379, 233);
    g2d.draw(new QuadCurve2D.Float(355, 212, 373, 204, 385, 213));
    g2d.draw(new QuadCurve2D.Float(355, 217, 373, 209, 385, 218));
    g2d.draw(new QuadCurve2D.Float(353, 235, 368, 230, 384, 235));
    g.drawLine(385, 213, 384, 259);
    // 오른뿔과 잇는
    g2d.draw(new QuadCurve2D.Float(386, 216, 402, 201, 414, 175));
    g.drawLine(384, 240, 401, 223);

    // 왼뿔 중간 그리기
    g.drawLine(167, 255, 159, 63);
    g.drawLine(167, 255, 239, 380);
    // 오른뿔 중간 그리기
    g.drawLine(447, 242, 447, 54);
    g.drawLine(447, 242, 382, 371);
    g.drawLine(410, 375, 382, 371);

    /////////////////////////////////////////////////
    /////////////////////////////////////////////////

    // 옆 뿔 채우기
    // 왼쪽
    g.setColor(new Color(240, 222, 0));// 노란색
    g.fillPolygon(new int[] { 187, 74, 79, 192 }, new int[] { 397, 402, 439, 434
    }, 4); // 왼쪽
    curve = new QuadCurve2D.Float(79, 439, 49, 440, 45, 416);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(74, 402);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(30, 300, 41, 312, 74, 402);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(45, 416);
    g2d.fill(path);
    // 오른쪽
    curve = new QuadCurve2D.Float(447, 385, 533, 385, 567, 380);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(563, 417);
    path.lineTo(443, 422);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(563, 417, 599, 420, 599, 387);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(567, 380);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(607, 270, 600, 280, 567, 380);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(583, 386);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(607, 270, 608, 329, 599, 387);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(583, 386);
    g2d.fill(path);

    // 명암
    g.setColor(new Color(232, 175, 76)); // 주황
    // 오른뿔
    g.fillPolygon(new int[] { 500, 516, 443, 447 }, new int[] { 385, 414, 418, 385 }, 4);

    newStroke = new BasicStroke(5f);
    g2d.setStroke(newStroke);
    g.setColor(new Color(220, 138, 56)); // 진한 주황
    // 오른뿔
    g.fillPolygon(new int[] { 444, 510, 520, 443 }, new int[] { 408, 404, 420, 422 }, 4);
    g.drawLine(446, 415, 573, 409);
    g2d.draw(new QuadCurve2D.Float(573, 409, 586, 404, 592, 391));

    g.setColor(new Color(255, 245, 107)); // 연한 노랑
    g.fillPolygon(new int[] { 606, 583, 567 }, new int[] { 273, 376, 380 }, 3);// 오른뿔
    g.fillPolygon(new int[] { 31, 55, 40 }, new int[] { 302, 351, 380 }, 3);// 왼뿔

    g.setColor(Color.white); // 흰색
    // 오른뿔
    g.drawLine(449, 397, 563, 392);
    g2d.draw(new QuadCurve2D.Float(563, 392, 583, 394, 586, 367));
    g.fillPolygon(new int[] { 584, 589, 601 }, new int[] { 365, 365, 295 }, 3);
    // 왼뿔
    g2d.setStroke(new BasicStroke(4f));
    g.drawLine(189, 407, 107, 411);
    g.drawLine(32, 306, 54, 405);
    g2d.setStroke(new BasicStroke(3f));
    g.drawLine(107, 411, 81, 412);
    g2d.setStroke(new BasicStroke(2f));
    g2d.draw(new QuadCurve2D.Float(81, 412, 62, 412, 62, 400));

    g.setColor(new Color(232, 175, 76)); // 주황
    // 왼뿔
    curve = new QuadCurve2D.Float(76, 425, 57, 426, 49, 396);
    g2d.draw(curve);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(48, 404);
    path.lineTo(51, 416);
    path.lineTo(59, 428);
    path.lineTo(72, 435);
    path.lineTo(192, 428);
    path.lineTo(190, 420);
    g2d.fill(path);
    g.drawLine(190, 420, 76, 425);

    g.setColor(new Color(240, 222, 0)); // 흰색 위 노랑
    g.fillPolygon(new int[] { 447, 504, 507, 446 }, new int[] { 395, 392, 397, 401 }, 4);// 오른뿔

    // 왼뿔 검은 부분
    g2d.setStroke(new BasicStroke(3f));
    g.setColor(new Color(100, 87, 48));
    g.drawLine(191, 429, 83, 435);
    g2d.setStroke(new BasicStroke(2f));
    curve = new QuadCurve2D.Float(80, 435, 51, 440, 48, 395);
    g2d.draw(curve);

    // 오른뿔 검은 부분
    g2d.setStroke(new BasicStroke(3f));
    g.setColor(new Color(82, 66, 41));
    g.drawLine(445, 418, 573, 413);
    curve = new QuadCurve2D.Float(573, 413, 591, 409, 593, 392);
    g2d.draw(curve);
    g.fillPolygon(new int[] { 592, 595, 601 }, new int[] { 391, 391, 338 }, 3);
    g2d.setStroke(oldStroke);
    g.drawLine(47, 393, 48, 402);
    g.drawLine(190, 428, 83, 435);

    // 목 부분 채색
    g.setColor(Color.white); // 흰색

    path = new GeneralPath();
    path.append(new QuadCurve2D.Float(335, 736, 327, 742, 319, 736), false);
    path.lineTo(320, 667);
    g2d.fill(path);
    path = new GeneralPath();
    path.append(new QuadCurve2D.Float(206, 674, 251, 703, 319, 736), false);
    path.lineTo(320, 667);
    g2d.fill(path);
    path = new GeneralPath();
    path.append(new QuadCurve2D.Float(206, 674, 177, 634, 169, 604), false);
    path.lineTo(320, 667);
    g2d.fill(path);
    g.setColor(new Color(188, 194, 205)); // 젤 오른쪽
    path = new GeneralPath();
    path.append(new QuadCurve2D.Float(483, 594, 473, 626, 442, 666), false);
    path.lineTo(320, 667);
    g2d.fill(path);
    g2d.setColor(new Color(205, 230, 224));// 오른쪽
    path = new GeneralPath();
    path.append(new QuadCurve2D.Float(335, 736, 382, 700, 442, 666), false);
    path.lineTo(439, 656);
    path.lineTo(326, 725);
    path.lineTo(328, 739);
    g2d.fill(path);

    g2d.setColor(new Color(205, 230, 224)); // 목 전체 채색
    g.fillPolygon(new int[] { 203, 277, 367, 425, 479, 442, 327, 208, 171, 176 },
        new int[] { 468, 573, 568, 476, 592, 658, 731, 666, 602, 592 }, 10);

    g2d.setColor(new Color(171, 201, 195)); // 목 채색
    g.fillPolygon(new int[] { 404, 439, 327, 323, 352, 364 }, new int[] { 508, 658, 728, 705, 569, 569 }, 6);
    g2d.setColor(new Color(184, 212, 207)); // 목 채색 오른쪽 밝은 명암
    g.fillPolygon(new int[] { 389, 404, 439, 390, 398 }, new int[] { 535, 507, 658, 687, 604 }, 5);
    g2d.setColor(new Color(145, 178, 167)); // 목 채색 오른쪽 어두운 명암
    g.fillPolygon(new int[] { 317, 323, 352, 315 }, new int[] { 641, 705, 569, 572 }, 4);

    g2d.setColor(new Color(165, 217, 209)); // 목 채색 왼쪽
    g.fillPolygon(new int[] { 241, 278, 316, 323, 243, 255 }, new int[] { 524, 574, 572, 724, 681, 586 }, 6);

    g2d.setColor(new Color(205, 230, 224)); // 하늘색
    g2d.fill(new QuadCurve2D.Float(140, 569, 153, 586, 176, 592)); // 목 왼쪽 장식
    curve = new QuadCurve2D.Float(140, 569, 173, 528, 198, 461);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(203, 468);
    path.lineTo(176, 592);
    g2d.fill(path);

    g.setColor(new Color(59, 76, 75)); // 명암
    curve = new QuadCurve2D.Float(157, 568, 167, 580, 180, 581);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(187, 555);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(157, 568, 181, 537, 199, 492);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(187, 555);
    g2d.fill(path);

    g2d.setColor(new Color(144, 160, 158)); // 목 오른쪽 장식
    g2d.fill(new QuadCurve2D.Float(504, 563, 492, 577, 475, 582));
    curve = new QuadCurve2D.Float(504, 563, 466, 510, 441, 449);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(436, 456);
    path.lineTo(458, 534);
    path.lineTo(475, 582);
    g2d.fill(path);

    g2d.setColor(new Color(95, 119, 119)); // 명암
    curve = new QuadCurve2D.Float(496, 562, 450, 505, 442, 450);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(437, 456);
    path.lineTo(467, 580);
    g2d.fill(path);

    g.setColor(new Color(34, 37, 32)); // 제일 진한 명암
    curve = new QuadCurve2D.Float(491, 563, 460, 532, 443, 473);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(453, 532);
    path.lineTo(471, 572);
    g2d.fill(path);

    // 목 오른쪽
    g2d.setColor(new Color(95, 119, 119));
    g.fillPolygon(new int[] { 428, 469, 439, 404 }, new int[] { 470, 616, 658, 508 }, 4);

    g.setColor(new Color(115, 136, 136)); // 반사광 덜 밝게
    g.fillPolygon(new int[] { 428, 469, 457, 417 }, new int[] { 470, 616, 633, 487 }, 4);

    g.setColor(new Color(188, 194, 205)); // 반사광 밝은
    curve = new QuadCurve2D.Float(437, 456, 471, 590, 483, 594);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(469, 616);
    path.lineTo(428, 470);
    g2d.fill(path);
    g.fillPolygon(new int[] { 473, 462, 467 }, new int[] { 602, 624, 600 }, 3);

    g.setColor(Color.white); // 빛 받는 곳

    curve = new QuadCurve2D.Float(149, 566, 174, 536, 200, 479);// 왼쪽 장식 커브
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(202, 466);
    path.lineTo(198, 462);
    path.lineTo(175, 519);
    path.lineTo(140, 569);
    g2d.fill(path);
    curve = new QuadCurve2D.Float(149, 566, 148, 574, 156, 583);
    path = new GeneralPath();
    path.append(curve, false);
    path.lineTo(141, 568);
    g2d.fill(path);

    g.fillPolygon(new int[] { 209, 198, 200, 218 }, new int[] { 476, 560, 572, 490 }, 4); // 왼쪽 빛 받는

    g.fillPolygon(new int[] { 240, 243, 216, 210 }, new int[] { 521, 524, 668, 666 }, 4);
    g2d.setStroke(new BasicStroke(4f));
    g.drawLine(212, 665, 323, 724);
    g.drawLine(212, 665, 174, 605);
    g.drawLine(315, 620, 282, 578);
    curve = new QuadCurve2D.Float(314, 573, 312, 650, 323, 727);
    g2d.draw(curve);

    g.setColor(new Color(59, 76, 75)); // 바로 아래 제일 진한 명암
    g.fillPolygon(new int[] { 317, 316, 277 }, new int[] { 573, 620, 573 }, 3);

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    newStroke = new BasicStroke(2f); // 5.0f는 두께를 나타냄
    g2d.setStroke(newStroke);
    // 옆 뿔 그리기
    // 왼쪽
    g.setColor(Color.BLACK);
    g.drawLine(187, 397, 74, 402);
    g2d.draw(new QuadCurve2D.Float(30, 300, 41, 312, 74, 402));
    g.drawLine(30, 300, 45, 416);
    g.drawLine(192, 434, 79, 439);
    g2d.draw(new QuadCurve2D.Float(79, 439, 49, 440, 45, 416));

    // 오른쪽
    g2d.draw(new QuadCurve2D.Float(447, 385, 533, 385, 567, 380));
    g2d.draw(new QuadCurve2D.Float(607, 270, 600, 280, 567, 380));
    g2d.draw(new QuadCurve2D.Float(607, 270, 608, 329, 599, 387));
    g.drawLine(443, 422, 563, 417);
    g2d.draw(new QuadCurve2D.Float(563, 417, 599, 420, 599, 387));

    // 왼뿔 바깥 그리기
    g.drawLine(214, 248, 278, 333);
    g.drawLine(214, 248, 159, 63);

    g.drawLine(120, 267, 159, 63);
    g.drawLine(120, 267, 186, 394);

    // 오른 뿔 바깥 그리기
    g.drawLine(397, 238, 341, 328);
    g.drawLine(397, 238, 447, 54);

    g.drawLine(504, 255, 447, 54);
    g.drawLine(504, 255, 448, 382);

    // 마스크
    g.drawLine(187, 396, 197, 459); // |
    g.drawLine(277, 574, 197, 459); // \
    g.drawLine(277, 574, 365, 569); // -
    g.drawLine(440, 450, 365, 569); // /
    g.drawLine(440, 450, 447, 386); // |

    // 덩어리 부분 두껍게
    g2d.draw(new QuadCurve2D.Float(385, 216, 402, 201, 414, 175)); // 오른뿔과 잇는
    g2d.draw(new QuadCurve2D.Float(228, 224, 204, 208, 190, 165)); // 왼뿔과 잇는
    g2d.draw(new QuadCurve2D.Float(258, 215, 244, 211, 228, 219)); // 가운데 뿔 왼쪽 장식
    g2d.draw(new QuadCurve2D.Float(355, 212, 373, 204, 385, 213));// 가운데 뿔 오룬쪽 장식
    curve = new QuadCurve2D.Float(258, 216, 288, 181, 301, 36);
    curve1 = new QuadCurve2D.Float(355, 212, 315, 152, 306, 49);
    g2d.draw(curve);
    g2d.draw(curve1);
    g.drawLine(301, 36, 306, 49);

    // 목부분 아래 채색 두꺼운 줄
    g2d.setStroke(new BasicStroke(5f));
    g.setColor(new Color(53, 76, 76));
    g2d.draw(new QuadCurve2D.Float(479, 593, 470, 616, 441, 657));
    g2d.draw(new QuadCurve2D.Float(335, 726, 382, 690, 441, 657));
    g2d.draw(new QuadCurve2D.Float(335, 726, 327, 732, 319, 726));
    g2d.draw(new QuadCurve2D.Float(209, 667, 251, 693, 319, 726));
    g2d.draw(new QuadCurve2D.Float(208, 667, 180, 624, 172, 604));

    // 목 부분 아래 빛 받는
    g.setColor(new Color(221, 233, 223)); // 흰색
    g2d.setStroke(oldStroke);
    g.drawLine(185, 629, 201, 655);
    g.drawLine(273, 702, 316, 724);
    g.drawLine(239, 685, 249, 691);
    g.setColor(Color.white);
    g.drawLine(332, 724, 343, 716);
    g.drawLine(367, 700, 382, 690);
    g.drawLine(432, 661, 439, 657);

    g2d.setStroke(new BasicStroke(2f));
    g.drawLine(170, 604, 183, 631);
    g.setColor(Color.BLACK);

    // 목 부분 두껍게 그리기
    g2d.draw(new QuadCurve2D.Float(437, 456, 471, 590, 483, 594));
    g2d.draw(new QuadCurve2D.Float(483, 594, 473, 626, 442, 666));
    g2d.draw(new QuadCurve2D.Float(335, 736, 382, 700, 442, 666));
    g2d.draw(new QuadCurve2D.Float(335, 736, 327, 742, 319, 736));
    g2d.draw(new QuadCurve2D.Float(206, 674, 251, 703, 319, 736));
    g2d.draw(new QuadCurve2D.Float(206, 674, 177, 634, 169, 604));
    g2d.draw(new QuadCurve2D.Float(202, 467, 181, 599, 169, 604));

    g2d.draw(new QuadCurve2D.Float(504, 563, 492, 577, 475, 582));
    g2d.draw(new QuadCurve2D.Float(504, 563, 466, 510, 441, 449));
    g2d.draw(new QuadCurve2D.Float(140, 569, 153, 586, 176, 592));
    g2d.draw(new QuadCurve2D.Float(140, 569, 173, 528, 198, 461));

    g2d.setStroke(oldStroke);

    g.setColor(Color.BLACK);
    // 마스크 안쪽
    g.drawLine(267, 497, 277, 574);
    g.drawLine(267, 497, 368, 494);
    g.drawLine(369, 562, 372, 493);
    g.drawLine(387, 474, 372, 493);

    // 마스크 중간 네모네모
    g.drawPolygon(new int[] { 274, 291, 294, 282 }, new int[] { 503, 502, 561, 561 }, 4); // 왼쪽
    g.drawPolygon(new int[] { 308, 325, 326, 312 }, new int[] { 501, 501, 559, 560 }, 4); // 가운데
    g.drawPolygon(new int[] { 340, 358, 357, 343 }, new int[] { 500, 499, 557, 558 }, 4); // 오른쪽

    // 마스크 왼쪽 네모네모
    g.drawPolygon(new int[] { 207, 212, 214, 209 }, new int[] { 443, 449, 471, 465 }, 4);
    g.drawPolygon(new int[] { 221, 225, 227, 222 }, new int[] { 459, 466, 492, 484 }, 4);
    g.drawPolygon(new int[] { 233, 237, 242, 236 }, new int[] { 474, 478, 512, 505 }, 4);
    g.drawPolygon(new int[] { 247, 253, 258, 251 }, new int[] { 491, 498, 532, 524 }, 4);

    // 마스크 오른쪽 네모네모
    g.drawPolygon(new int[] { 380, 386, 384, 378 }, new int[] { 492, 485, 522, 529 }, 4);
    g.drawPolygon(new int[] { 395, 402, 401, 395 }, new int[] { 474, 463, 493, 500 }, 4);
    g.drawPolygon(new int[] { 411, 416, 416, 412 }, new int[] { 454, 445, 468, 472 }, 4);
    g.drawPolygon(new int[] { 424, 429, 429, 425 }, new int[] { 435, 429, 451, 456 }, 4);

    // 목 선 얇은 부분
    curve = new QuadCurve2D.Float(316, 572, 315, 650, 326, 727);
    g2d.draw(curve);
    g.drawLine(405, 508, 438, 651);
    g.drawLine(243, 524, 217, 664);

    g.setColor(new Color(201, 229, 255));
    g.setFont(new Font("마루 부리 Beta", Font.ITALIC, 15));
    g.drawString("컴퓨터공학과 20211197", 650, 30);
    g.setFont(new Font("마루 부리 Beta", Font.BOLD, 15));
    g.drawString("입니다", 711, 53);
    g.setColor(new Color(115, 218, 226));
    g.setFont(new Font("마루 부리 Beta", Font.BOLD, 20));
    g.drawString("김희주", 650, 53);

  }
}
