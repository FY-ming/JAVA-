package JAVA_EX_work06;

import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame 
{
   private JTextField enterField; // inputs message from user
   private JTextArea displayArea; // display information to user
   private ObjectOutputStream output; // output stream to client
   private ObjectInputStream input; // input stream from client
   private ServerSocket server; // server socket
   private Socket connection; // connection to client
   private int counter = 1; // counter of number of connections

   // set up GUI
   public Server()
   {
      super( "服务器" );

      enterField = new JTextField(); // create enterField
      enterField.setEditable( false );
      enterField.addActionListener(
         new ActionListener()
         {
            // send message to client
            public void actionPerformed( ActionEvent event )
            {
               sendData( event.getActionCommand() );
               enterField.setText( "" );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener

      add( enterField, BorderLayout.NORTH );

      displayArea = new JTextArea();
      displayArea.setEditable(false);  // 不可编辑
      JScrollPane scrollPane = new JScrollPane(displayArea);  // 创建滚动面板
      scrollPane.setPreferredSize(new Dimension(400, 250));  // 设置滚动面板大小
      add(scrollPane, BorderLayout.CENTER);  // 将滚动面板添加到布局中

      setSize( 400, 300 ); // set size of window
      setLocationRelativeTo(null);
      setVisible( true ); // show window
   } // end Server constructor

   // set up and run server 
   public void runServer()
   {
      try {
         server = new ServerSocket(12345, 100); // serverSocket，端口12345

         while (true) {
            try {
               waitForConnection(); // 等待连接
               getStreams(); // 获取输入和输出流
               processConnection(); // 处理连接
            } catch (EOFException eofException) {
               displayMessage("\n服务器终止连接");
            } catch (IOException ioException) {
               // 如果发生异常（比如连接被重置），输出错误信息并跳回等待连接
               displayMessage("\n发生网络错误，正在重新连接...");
            } finally {
               closeConnection(); // 关闭连接
               counter++; // 增加连接计数
            }
         }
      } catch (IOException ioException) {
         // 如果服务器启动过程中出错，输出异常并结束程序
         ioException.printStackTrace();
      }
   } // end method runServer

   // 等待客户端连接
   private void waitForConnection() throws IOException
   {
      displayMessage( "服务器正在运行，等待客户端连接...\n" );
      connection = server.accept(); // 接收客户端信息
      displayMessage( "连接 " + counter + " 成功接收: " +
         connection.getInetAddress().getHostName() );
   } // end method waitForConnection

   // 创建输入输出流
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream( connection.getOutputStream() );
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream( connection.getInputStream() );

      displayMessage( "\n创建输入输出流\n" );
   } // end method getStreams

   // 服务器与客户端交互
   private void processConnection() throws IOException
   {
      String message = "连接成功\n";
      displayMessage( message ); // send connection successful message

      // enable enterField so server user can send messages
      setTextFieldEditable( true );

      do // process messages sent from client
      { 
         try // read message and display it
         {
            //针对客户端的各个信息做出对应处理
            message = ( String ) input.readObject(); // 接收客户端的信息
            switch (message)
            {
               case "CLIENT >>> CLIENT_LOGIN"://登录
                  sendData("SERVER >>> LOGIN_SUCCESS");
                  displayMessage( "服务器 >>> 用户登录\n" );
                  message = ( String ) input.readObject();
                  displayMessage( "用户："+message +'\n');
                  break;
               case "CLIENT >>> UpLoadFile_SUCCESS"://登录
                  sendData("SERVER >>> UpLoadFile_SUCCESS");
                  displayMessage( "服务器 >>> 文件上传成功\n" );
                  break;
               case "CLIENT >>> SetUsersInfo_SUCCESS"://登录
                  sendData("SERVER >>> SetUsersInfo_SUCCESS");
                  displayMessage( "服务器 >>> 用户信息修改成功\n" );
                  break;
               case "CLIENT >>> SetPassword_SUCCESS"://登录
                  sendData("SERVER >>> SetPassword_SUCCESS");
                  displayMessage("服务器 >>> 修改密码成功\n");
                  break;
               case "CLIENT >>> AddUser_SUCCESS"://登录
                  sendData("SERVER >>> AddUser_SUCCESS");
                  displayMessage( "服务器 >>> 用户添加成功\n" );
                  break;
               case "CLIENT >>> DeleteUser_SUCCESS"://登录
                  sendData("SERVER >>> DeleteUser_SUCCESS");
                  displayMessage( "服务器 >>> 删除用户成功\n" );
                  break;
               case "CLIENT >>> DownloadFile_SUCCESS"://登录
                  sendData("SERVER >>> DownloadFile_SUCCESS");
                  displayMessage( "服务器 >>> 文件下载成功\n" );
                  break;

               default:
//                  message = ( String ) input.readObject();
//                  displayMessage( message +'\n');
                  break;
            }

         } // end try
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            displayMessage( "\n接收到未知数据" );
         } // end catch

      } while ( !message.equals( "CLIENT>>> TERMINATE" ) );
   } // end method processConnection

   // close streams and socket
   private void closeConnection() 
   {
      displayMessage( "\n终止连接\n" );
      setTextFieldEditable( false ); // disable enterField

      try {
         if (output != null) {
            output.close(); // 关闭输出流
         }
         if (input != null) {
            input.close(); // 关闭输入流
         }
         if (connection != null) {
            connection.close(); // 关闭连接
         }
      } catch (IOException ioException) {
         ioException.printStackTrace(); // 打印异常信息
      }
   } // end method closeConnection

   // send message to client
   public void sendData( String message )
   {
      try // send object to client
      {
         output.writeObject( message );
         output.flush(); // flush output to client
         //displayMessage( "\nSERVER>>> " + message );
      } // end try
      catch ( IOException ioException ) 
      {
         displayArea.append( "\n无法写入" );
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
               displayArea.append( messageToDisplay ); // append message
            } // end method run
         } // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method displayMessage

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
         }  // end inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method setTextFieldEditable

   public static void main( String args[] )
   {
      Server application = new Server(); // create server
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.runServer(); // run server application
   } // end main
} // end class Server

