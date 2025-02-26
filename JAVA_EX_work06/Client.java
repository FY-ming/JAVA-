package JAVA_EX_work06;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame 
{
   private JTextField enterField; // enters information from user
   private JTextArea displayArea; // display information to user
   ObjectOutputStream output; // output stream to server
   private ObjectInputStream input; // input stream from server
   private String message = ""; // message from server
   private String chatServer; // host server for this application
   private Socket client; // socket to communicate with server
   public String username;

   // initialize chatServer and set up GUI
   public Client( String host )
   {
      super( "客户端" );

      chatServer = host; // set server to which this client connects

      enterField = new JTextField(); // create enterField
      enterField.setEditable( false );
      enterField.addActionListener
      //捕捉回车清空并发送信息
      (
         new ActionListener() 
         {
            // send message to server
            public void actionPerformed( ActionEvent event )
            {
               sendData( event.getActionCommand() );
               enterField.setText( "" );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      add( enterField, BorderLayout.NORTH );

      displayArea = new JTextArea();  // 创建显示区域
      displayArea.setEditable(false);  // 设置为不可编辑
      JScrollPane scrollPane = new JScrollPane(displayArea);  // 创建滚动面板
      scrollPane.setPreferredSize(new Dimension(400, 250));  // 设置滚动面板大小
      add(scrollPane, BorderLayout.CENTER);  // 将滚动面板添加到布局中

      setSize( 400, 300 ); // set size of window
      //setLocationRelativeTo(null);
      setVisible( true ); // show window

      // 捕获窗口关闭事件
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            // 关闭连接前执行关闭操作
            closeConnection();
         }
      });

   } // end Client constructor

   // connect to server and process messages from server
   public void runClient() 
   {
      try // connect to server, get streams, process connection
      {
         connectToServer(); // create a Socket to make connection
         getStreams(); // get the input and output streams
         processConnection(); // process connection
      } // end try
      catch ( EOFException eofException ) 
      {
         displayMessage( "\n客户端终止连接" );
      } // end catch
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
      finally 
      {
         closeConnection(); // close connection
      } // end finally
   } // end method runClient

   // connect to server
   public void connectToServer() throws IOException
   {      
      displayMessage( "尝试连接\n" );

      // create Socket to make connection to server
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

      // display connection information
      displayMessage( "连接至: " +
         client.getInetAddress().getHostName() );
   } // end method connectToServer

   // get streams to send and receive data
   public void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream( client.getOutputStream() );      
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream( client.getInputStream() );

      displayMessage( "\n创建输入输出流\n" );
   } // end method getStreams

   // process connection with server
   private void processConnection() throws IOException
   {
      // enable enterField so client user can send messages
      setTextFieldEditable( true );

      do // process messages sent from server
      { 
//         try // read message and display it
//         {
//
//
//
//
//         } // end try
//         catch ( ClassNotFoundException classNotFoundException )
//         {
//            displayMessage( "\n接收未知数据" );
//         } // end catch

      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
   } // end method processConnection

   public void closeConnection() {
      try {
         // 1. 发送退出请求到服务器
         sendData("CLIENT>>> TERMINATE");

         // 2. 关闭输入流和输出流
         if (input != null) {
            input.close();  // 关闭输入流
         }
         if (output != null) {
            output.close();  // 关闭输出流
         }

         // 3. 关闭套接字连接
         if (client != null) {
            client.close();  // 关闭套接字
         }

         displayMessage("\n连接已关闭");

      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }


   // send message to server
   public void sendData(String message)
   {
      try // send object to server
      {
         output.writeObject(  message );
         output.flush(); // 刷新输出流，确保消息被发送
         //displayMessage( "\nCLIENT>>> " + message );
      } // end try
      catch ( IOException ioException )
      {
         displayArea.append( "\n错误写入" );
      } // end catch
   } // end method sendData

   // manipulates displayArea in the event-dispatch thread
   public void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() // updates displayArea
            {
               displayArea.append( messageToDisplay );
            } // end method run
         }  // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method displayMessage

   // 处理接收服务器信息的函数
   public String receiveServerMessage() {
      try {
         // 读取服务器发来的消息
         String message = (String) input.readObject();
         return message;
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
         return null;  // 出现错误时返回 null
      }
   }


   // manipulates enterField in the event-dispatch thread
   private void setTextFieldEditable( final boolean editable )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // sets enterField's editability
            {
               enterField.setEditable( editable );
            } // end method run
         } // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method setTextFieldEditable

   public static void main( String args[] )
   {
      Client application; // declare client application

      // if no command line args
      if ( args.length == 0 )
         application = new Client( "127.0.0.1" ); // connect to localhost
      else
         application = new Client( args[ 0 ] ); // use args to connect

      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.runClient(); // run client application
   } // end main
} // end class Client
