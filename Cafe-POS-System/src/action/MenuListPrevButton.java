package action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuListPrevButton implements ActionListener {

   private JPanel cardPanel;
   private JLabel pageLabel;
   private ArrayList<JPanel> panels;
   
   public MenuListPrevButton(JPanel cardPanel, JLabel pageLabel, ArrayList<JPanel> panels) {
      this.cardPanel = cardPanel;
      this.pageLabel = pageLabel;
      this.panels = panels;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      CardLayout card = (CardLayout)cardPanel.getLayout();
      
      // 첫번째 인덱스가 아니면, previous 적용
      if (!panels.get(0).isVisible()) {
         card.previous(cardPanel);
      }
   }
}