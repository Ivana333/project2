package project1main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.*;
import java.util.*;
import javax.swing.JScrollPane;

public class project1main extends JPanel implements ActionListener {

   static JButton button;
   static JButton OK;
   //static JButton Save; 
   static JRadioButton Left;
   static JRadioButton Right;
   
   
   JLabel  InfoLabel;
   JFileChooser chooser;
   String choosertitle;
   static JTextArea text1 = new JTextArea(8,50);
   JScrollPane scrollPane = new JScrollPane(text1); 
 static JTextArea text2 =new JTextArea(1,1);
 static JTextArea text3 =new JTextArea(1,1);
 static JTextArea text4 =new JTextArea(1,1);
 static JTextArea text5 =new JTextArea(1,1);
 static JTextArea text6 =new JTextArea(1,1);

 
 
  public project1main() {
   button = new JButton("Select file ");
   button.addActionListener(this);
   add(button);
   Right = new JRadioButton("Right");
   Left = new JRadioButton("Left");

  

   OK = new JButton("OK");
   OK.addActionListener(this);
   add(OK);
   
   
  
   add(text1);
   final String FileWriter = null;
String input = "";
String outforthis = "";
   button.addActionListener(new ActionListener() {
      @Override

      public void actionPerformed(ActionEvent e) {
         if (chooser == null) {
               chooser = new JFileChooser();
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);
               chooser.addChoosableFileFilter(new FileFilter() {
                  @Override
                  public boolean accept(File f) {
                    // input = f.getName();
                     return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                  }

                  @Override
                  public String getDescription() {
                     return "Text Files (*.txt)";
                  }
               });
         }

         switch (chooser.showOpenDialog(project1main.this)) {
               case JFileChooser.APPROVE_OPTION:
                  try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                     text1.setText(null);
                     String text = null;
                     while ((text = br.readLine()) != null) {
                           text1.append(text);
                           text1.append("\n");
                     }
                     text1.setCaretPosition(0);
                  } catch (IOException exp) {
                     exp.printStackTrace();
                     JOptionPane.showMessageDialog(project1main.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
                  }
                  break;
         }



      }
   });


   OK.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e)
   {
      Scanner scanner;
      PrintWriter writer;
      Scanner scanner2;
      PrintWriter writer2;
      StringWriter w;
      //StringWriter writer2;
      int countblank=0;
      int countlines=0;
      int counttotal =0;
      int countWord=0;
      int ch;
      int charsCount = 0;
      try
      {

         scanner = new Scanner(chooser.getSelectedFile());
         writer = new PrintWriter("intermediate.txt");

         while(scanner.hasNext())
         {

               String line = scanner.nextLine();
               if (!line.isEmpty()) {
                  writer.write(line);
                  writer.write(" ");
                  countlines++;
                  //String[] words = line.split(" ");
                  
                  charsCount += line.length();
                  //Updating the wordCount
                  String[] words = line.split("\\s+");
                  for (String word : words) {
                      if (!word.matches("\\p{Punct}+")) {
                          countWord++;
                      }
                  }
                // countWord = countWord + words.length;
               }
               counttotal++;

         }
        int  blanklines= counttotal -countlines;
        text2.append(Integer.toString(blanklines));
         System.out.println(" # of blank lines removed: " + blanklines);// # blank lines removed
        System.out.println(countWord);// #lines
        text3.append(Integer.toString(countWord));
         scanner.close();
         writer.close();
      } catch (FileNotFoundException e1)
      {

         e1.printStackTrace();
      }
      try
      {
         int charlength = 80;
           FileReader in = new FileReader("intermediate.txt");
           BufferedReader br = new BufferedReader(in);
           scanner2 = new Scanner(br);
           // writer2 = new  PrintWriter("output.txt");
           w = new StringWriter();
           writer2 = new PrintWriter(w);
            //writer2 = new StringWriter();
           
           int lines=0;
           StringBuilder buildertext = new StringBuilder(charlength);

           scanner2.useDelimiter("");
           
           
           while(scanner2.hasNext())
           {
        
          
               //string buffer created a new scanner 
              Scanner newScan = new Scanner(scanner2.nextLine());
              
              while (newScan.hasNext()) {
                 
                 
                  String after = newScan.next();
                  System.out.println(after);
                  if ( (buildertext.length()+ after.length() +1) > charlength)  
                  {
                      if(Right.isSelected())
                      {
                         buildertext.append('\n');
                        // toString();
                        writer2.write(String.format("%80s",buildertext.toString() ));
                         buildertext = new StringBuilder(after);
                         lines++;
                      }
                      else
                      {
                         buildertext.append('\n');
                         writer2.write(buildertext.toString());
                         buildertext = new StringBuilder(after);
                         
                         lines++;
                      
                      }
                  }
                  else 
                  {
                     buildertext.append((buildertext.length() == 0 ? "" :" ") +after);
                  }
                  
                  
              }
              
              writer2.write(buildertext.toString());
           
             
              newScan.close();
              
              JFileChooser chooser = new JFileChooser();
              //String sb = buildertext.toString();
              //writer2.write(buildertext.toString() + "\n");
               String out = w.toString();
               chooser.setCurrentDirectory(new File("/home/me/Documents"));
               int retrival = chooser.showSaveDialog(null);
               if (retrival == JFileChooser.APPROVE_OPTION) {
                   try {
                      //outforthis = chooser.getName();
                      if(input == outforthis)
                      {
                    	  
                      }
                     // chooser.getSelectedFile();
                     //String sb= writer2.write(buildertext.toString());
                      
                      FileWriter f = new FileWriter(chooser.getSelectedFile()+".txt");
                      //String sb = writer2.write(buildertext.toString()) ;
                      f.write(out);
                       f.close();
                   } 
                   catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }   
              
      }     
              


writer2.close();
//w.close();
int lines1= lines  ;
double linelength= (double)(charsCount)/lines1; 
double averageWords= (double)(countWord)/lines1;

System.out.println("# of lines: " + lines1);

//System.out.println("Number of: " + lines1);
text4.append(Integer.toString(lines1));
text5.append(Double.toString(averageWords));
text6.append(Double.toString(linelength));
     System.out.println (averageWords);
      }
       catch (FileNotFoundException a)
       {
          a.printStackTrace();
      }

   }
  });

}

  public Dimension getPreferredSize(){
   return new Dimension(800, 300);
   }

  public static void main(String s[]) {








   JLabel  InfoLabel;
   JLabel  Analysis;
   JLabel OutputLabel;

   JLabel OutputLabel2;
   JLabel OutputLabel3;
   JLabel OutputLabel4;
   JLabel OutputLabel5;
   JLabel OutputLabel6; 
   //JRadioButton Right;
   //JRadioButton Left;


      JFrame frame = new JFrame("Project 1");

   JPanel panel1 = new JPanel();

   JPanel panel2 = new JPanel();

   JPanel panel3 = new JPanel();

   JPanel panel4 = new JPanel();

   JPanel panel5 = new JPanel();



   InfoLabel = new JLabel("Please select a text file");
   Analysis = new JLabel("Input File :");
   OutputLabel = new JLabel( " #blank lines removed:");
   OutputLabel2 = new JLabel( " # lines:");
   OutputLabel3 = new JLabel( " # words processed:");
   OutputLabel4 = new JLabel( " average  words/line:");
   OutputLabel5 = new JLabel( " average line length:");
   //..Create buttons
   





   project1main   panel = new project1main();

   ButtonGroup group = new ButtonGroup();

   panel5.add(InfoLabel);
   panel4.add(Analysis);
   
   panel3.add(Left);
   panel3.add(Right);
   group.add(Left);
   group.add(Right);

   Left.setSelected(true);

   panel3.add(OK);
   panel5.add(button);
   
   JScrollPane n = new JScrollPane(text1);
  // n.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  // n.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
   

   //panel4.add(text1);
   panel4.add(n);
   //panel1.setVisible(true);
   //panel3.add(Save);


   frame.add(panel);
   panel.add(panel1);
   panel.add(panel2);
   panel.add(panel3);
   panel.add(panel4);
   panel.add(panel5);
   
   panel.setLayout(new BorderLayout());
   panel.add(panel2, BorderLayout.NORTH);
   panel.add(panel3, BorderLayout.EAST);
   panel.add(panel4, BorderLayout.CENTER);
   panel.add(panel5, BorderLayout.WEST);
   panel.add(panel1, BorderLayout.SOUTH);
   
  
   panel1.setLayout( new GridLayout(10,10));
   panel1.add(OutputLabel);
   panel1.add(text2);

   panel1.add(OutputLabel2);
   panel1.add(text4);
   panel1.add(OutputLabel3);
   panel1.add(text3);
   panel1.add(OutputLabel4); 
   panel1.add(text5);
   panel1.add(OutputLabel5); 
   panel1.add(text6);
 
  
   
  
   
  // panel5.setLayout(new GridLayout(4,1));

   frame.addWindowListener(
   new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
         }
      }
   );
   frame.getContentPane().add(panel,"Center");
   frame.setSize(panel.getPreferredSize());
   frame.setVisible(true);
   }

@Override
public void actionPerformed(ActionEvent e) {
   // TODO Auto-generated method stub

}


//static void formatLines( File chooser.getSelectedFile(), int maxLength,  Locale currentLocale) {

 

}


