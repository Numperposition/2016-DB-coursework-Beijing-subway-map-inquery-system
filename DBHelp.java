import java.sql.*;
import java.util.*;
public class DBHelp {
	
	private int[] sc, tc;
	int []RealSubNoTrail = new int[5]; 
	ArrayList<String> staNameRoute;
	int[] totalDisArray = new int[5]; 
	int totalStation = 0;
	int[] stationPassNum = new int[5];
	private int[][] array = {{1,1,0,1,1,0,0,0,1,1,0,0,0,1,0,0},
		   {1,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0},
		   {0,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0},
		   {1,1,0,1,0,1,1,0,1,1,0,0,1,0,0,0},
		   {1,1,0,0,1,1,1,0,0,1,1,0,1,0,1,0},
		   {0,1,0,1,1,1,0,1,1,1,0,0,0,0,0,0},
		   {0,0,0,1,1,0,1,0,1,1,0,0,0,0,0,0},
		   {0,1,0,0,0,1,0,1,0,1,0,0,1,0,1,1},
		   {1,0,0,1,0,1,1,0,1,1,0,1,0,0,0,0},
		   {1,0,1,1,1,1,1,1,1,1,1,0,1,0,0,0},
		   {0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0},
		   {0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0},
		   {0,1,1,1,1,0,0,1,0,1,0,0,1,0,1,1},
		   {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
		   {0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,0},
		   {0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1}};
	  String startStation = new String();
	  String terminalStation = new String();
	  String PstartStation = "Summer Palace";        
	  String PterminalStation = "Temple of Heaven";   //Change these two strings to test the route from one place to another.
	  ArrayList<Integer> nextDis = null;
	  int[] totalDisAdd = new int[5];
	  int totalDis = 0;
	  public void placeStation(Connection conn)
	{
		  try{
		  Statement s1 = conn.createStatement();
		  ResultSet rset = s1.executeQuery(
					"select staName from station natural join place "
					+ "where placeName = '"+PstartStation+"'");
		  if(rset.next());
		  {
			  startStation=rset.getString("staName");
		  }
		  Statement s2 = conn.createStatement();
		  ResultSet rset1 = s2.executeQuery(
					"select staName from station natural join place "
					+ "where placeName = '"+PterminalStation+"'");
		  if(rset1.next());
		  {
			  terminalStation=rset1.getString("staName");
		  }
		  
		  }catch(SQLException sqle){};

		  
		  
	}
	
	public  Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/beijingsubway"    
					, "root" , "xbwbob" );      //This is the "root"connection and the password is "xbwbob". 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return connection;
		
	}
	private  void ChangeSubNo(Connection conn){
		int [] subNoTrail = new int[5];
		int flag1 = 0, flag2 = 0, flag3 = 0;
		int tempTotalDis = 9999999;
		for(int i = 0; i < sc.length; i++)
		{
			for(int k = 0; k < tc.length; k++){
				if(sc[i] == tc[k])  //At the same line
				{
					RealSubNoTrail[0] = sc[i];
					RealSubNoTrail[1] = tc[k];
					flag1 = 1;
				}
				if(array[sc[i] - 1][tc[k] - 1] == 1  )  //Transfer once
				{
					
					subNoTrail[0] = sc[i];
					subNoTrail[1] = tc[k];
					chooseRoute(conn, subNoTrail,0);
					
					flag2 = 1;
					if(totalDis <= tempTotalDis)
					{
						RealSubNoTrail[0] = sc[i];
						RealSubNoTrail[1] = tc[k];
						tempTotalDis = totalDis;
					}
				}
			}
		}
		if(flag1 == 0 && flag2 == 0)
		{
			for(int i = 0; i < sc.length; i++){
			for(int j = 0; j < 16; j++)
			{ 
			 if(array[sc[i]-1][j] == 1 && j != sc[i] - 1)
			  {
					for(int k = 0; k < tc.length; k++)
				 {
				    if(array[j][tc[k] - 1] == 1)   //Transfer twice
				   {
					   
				   
				      subNoTrail[0] = sc[i];
					  subNoTrail[1] = j + 1;
					  subNoTrail[2] = tc[k];
				       chooseRoute(conn, subNoTrail,0);
				       if(totalDis < tempTotalDis)
				       {
				    	   RealSubNoTrail[0] = sc[i];
						   RealSubNoTrail[1] = j + 1;
						   RealSubNoTrail[2] = tc[k];
						    tempTotalDis = totalDis;
				       }
					   
					   flag3 = 1;
				   }
				  }		
			   }	  
		     }
		   }
		
			
		}
		if(flag1 == 0 && flag2 == 0 && flag3 == 0)
		{
			for(int i = 0; i < sc.length; i++)
		   {
			   for(int j = 0; j < 16; j++)
			   {
				   if(array[sc[i]-1][j] == 1 && j != sc[i] - 1)
				   {
					   for(int k = 0; k < 16; k++)
					   {
						   if(array[j][k] == 1 && k != j)
						   {
							   for(int o = 0; o < tc.length; o++)
							   {
								   if(array[k][tc[o] - 1] == 1)
								   {
									   
									   subNoTrail[0] = sc[i];
									   subNoTrail[1] = j + 1;
									   subNoTrail[2] = k + 1;
									   subNoTrail[3] = tc[o];
									   chooseRoute(conn, subNoTrail, 0);
									   
									   if(tempTotalDis >= totalDis)
									   {
										   RealSubNoTrail[0] = sc[i];
										   RealSubNoTrail[1] = j + 1;
										   RealSubNoTrail[2] = k + 1;
										   RealSubNoTrail[3] = tc[o];
										   tempTotalDis = totalDis;
									   }
									  
								   }
							   }
						   }
						   
						   
					   }
				   }
			   }
		   }
		}
		   
	
	}
	private void query(Connection conn){
		 int[] s = new int[5];
		 int[] t = new int[5];
		int i = 0;
		int j = 0;
		try{
			Statement s1 = conn.createStatement(); 
			
			ResultSet rset = s1.executeQuery(
					"select subNo from station  "
					+ "where staName = '"+startStation+"'");
			i = 0;
			while(rset.next())
			{
				s[i] = rset.getInt("subNo");
				i++;	
			}
			s1.close();
			Statement t1 = conn.createStatement(); 
			
			rset = t1.executeQuery(
					"select subNo from station  "
					+ "where staName = '"+terminalStation+"'");
			j = 0;
			while(rset.next())
			{
				t[j] = rset.getInt("subNo");
				j++;	
			}
			t1.close();
			
		}catch(SQLException sqle){System.out.println("error");}
		sc = new int[i];
		tc = new int[j];
		for(int p = 0; p < i; p++)
			sc[p] = s[p];
		for(int q = 0; q < j; q++)
			tc[q] = t[q];
	}
	
	public void chooseRoute(Connection conn, int[] subNoTrail, int FLAG){
		int i = 0, num = 0, k = 0;
		ArrayList<String> transferStaName ;
		ArrayList<Integer> staNum;
		staNameRoute = new ArrayList<String>();
		//保存所有换乘站的数组.包括起点和终点.若没有，则只有起点和终点
		totalDis = 0;
		totalStation = 0;
	    staNameRoute.add(startStation); 
	    while(subNoTrail[num] != 0)
	     num++;
	    if(num == 4 || num == 3)
	    {
	    	k = num - 1;
	    	while(i < k - 1)
	    	{
	    		
	    		transferStaName = new ArrayList<String>();
	    		staNum = new ArrayList<Integer>();
	 
	    		try{
				Statement s1 = conn.createStatement(); 
				ResultSet rset = s1.executeQuery(
			"select staName, staNo from station where staName in "
			+ "(select staName FROM station "
			+ "WHERE subNo = '"+subNoTrail[i]+"') and subNo = '"+subNoTrail[i+1]+"'");
			//找出集合中与两条不同地铁线间的中转站(1-2个).
				while(rset.next())
				{
					transferStaName.add(rset.getString("staName"));
					staNum.add(rset.getInt("staNo"));
				}		
				s1.close();
		       }catch(SQLException sqle){System.out.println("----error");}
	         
			  if(transferStaName.size() == 2)//第一个换乘站有两条线
		    	{
				    if(subNoTrail[i] != 2 && subNoTrail[i] != 10)
				    {
				    	try{
		    			   Statement s2 = conn.createStatement();
		    			   ResultSet rset = s2.executeQuery(
		    					   "select staNo from station "
		    					   + "where subNo = '"+subNoTrail[i]+"' and staName = '"+staNameRoute.get(i)+"'");
		    			   if(rset.next())
			    			  {
			    				  if((Math.abs(rset.getInt("staNo") - staNum.get(0))) > (Math.abs(rset.getInt("staNo") - staNum.get(1))))
			 		            staNameRoute.add(transferStaName.get(1));
			 		              else
			 		        	staNameRoute.add(transferStaName.get(0));
			    			  }
		    			   s2.close();
				    	}catch(SQLException sql){}
				    }
				    else
				    {
				    	int total=0,starNo=0;
				    	try{
					        Statement s3 = conn.createStatement();
		    			   ResultSet rset2 = s3.executeQuery("select totalNum from subline where subNo = '"+subNoTrail[i]+"'");
		    			   Statement s4 = conn.createStatement();
		    			   ResultSet rset3 = s4.executeQuery("select staNo from station where staName='"+staNameRoute.get(i)+"'and subNo = '"+subNoTrail[i]+"'");
		    			   if(rset2.next())
		    			   {
		    				   total=rset2.getInt("totalNum");
		    			       
		    			   }
		    		
		    			   if(rset3.next())
		    			      starNo=rset3.getInt("staNo");
		    			  // System.out.println("start = " + starNo);
		    			   s3.close();
		    			   s4.close();
		    			   }catch(SQLException sql){}
				    	   
				    	int way1=0,way2=0;
						   if(staNum.get(0)>staNum.get(1))
	                       {
	                            if(starNo<staNum.get(1))
	                            {
	                            	way1=staNum.get(1)-starNo;
	                            	way2=starNo+(total-staNum.get(0));
	                            	if(way1>way2)
	                            		staNameRoute.add(transferStaName.get(0));
	                            	else 
	                            		staNameRoute.add(transferStaName.get(1));
	                            }
	                            else if(starNo>staNum.get(0))
	                            {
	                            	way1=starNo-staNum.get(0);
	                            	way2=starNo+staNum.get(1);
	                            	if(way1>way2)
	                                   staNameRoute.add(transferStaName.get(1));
	                            	else
	                            	   staNameRoute.add(transferStaName.get(0));
	                            }
	                            else
	                            {
	                            	way1=Math.abs(staNum.get(1)-starNo);
	                            	way2=Math.abs(staNum.get(0)-starNo);
	                            	if(way1>way2)
	                            		staNameRoute.add(transferStaName.get(0));
	                            	else
	                            		staNameRoute.add(transferStaName.get(1));
	                            		
	                            }
	                            
	                       }
						   else
						   {
							   if(starNo<staNum.get(0))
	                           {
	                           	way1=staNum.get(0)-starNo;
	                           	way2=starNo+(total-staNum.get(1));
	                           	if(way1>way2)
	                           		staNameRoute.add(transferStaName.get(1));
	                           	else 
	                           		staNameRoute.add(transferStaName.get(0));
	                           }
	                           else if(starNo>staNum.get(1))
	                           {
	                           	way1=starNo-staNum.get(1);
	                           	way2=starNo+staNum.get(0);
	                           	if(way1>way2)
	                                  staNameRoute.add(transferStaName.get(0));
	                           	else
	                           	   staNameRoute.add(transferStaName.get(1));
	                           }
	                           else
	                           {
	                           	way1=Math.abs(staNum.get(1)-starNo);
	                           	way2=Math.abs(staNum.get(0)-starNo);
	                           	if(way1>way2)
	                           		staNameRoute.add(transferStaName.get(0));
	                           	else
	                           		staNameRoute.add(transferStaName.get(1));
	                           		
	                           }
						   }
				    	
				    }
		    			   
		 		         
		        }
			   else
				  staNameRoute.add(transferStaName.get(0));
			  transferStaName = null;
			  staNum = null;
			  i++;
	    	}
	    	transferStaName = new ArrayList<String>();
	    	staNum = new ArrayList<Integer>();
	    	//对后两个地铁线交点进行判断
	    	try{
				Statement s1 = conn.createStatement(); 
				ResultSet rset = s1.executeQuery(
			"select staName from station where staName in "
			+ "(select staName FROM station "
			+ "WHERE subNo = '"+subNoTrail[k-1]+"') and subNo = '"+subNoTrail[k]+"'");
			//找出集合中与两条不同地铁线间的中转站(1-2个).
				while(rset.next())
				{
					transferStaName.add(rset.getString("staName"));
					staNum.add(rset.getInt("staNo"));

				}		
		       }catch(SQLException sqle){}
			  //int p = 0, q = 0;
			  if(transferStaName.size() == 2)//第一个换乘站有两条线
		    	{
				  if(subNoTrail[k] != 2 && subNoTrail[k-1] != 10)
					   //若不等于2号线或10号线.
				   {
					//  System.out.println("tfsn = " + staNum.get(0)+staNum.get(1));
					   try{
						  
		    			   Statement s2 = conn.createStatement();
		    			   ResultSet rset = s2.executeQuery(
		    					   "select staNo from station "
		    					   + "where subNo = '"+subNoTrail[k - 1]+"' and staName = '"+staNameRoute.get(staNameRoute.size() - 1)+"'");
		    			  if(rset.next())
		    			  {
		    				  if((Math.abs(rset.getInt("staNo") - staNum.get(0))) > (Math.abs(rset.getInt("staNo") - staNum.get(1))))
		 		            staNameRoute.add(transferStaName.get(1));
		 		              else
		 		        	staNameRoute.add(transferStaName.get(0));
		    			  }
		    			s2.close();
		 		        
		                 }catch(SQLException sql){}
					   
				   }
				   else
				  {     
                    int total=0, starNo=0;
					 try{
				        Statement s3 = conn.createStatement();
	    			   ResultSet rset2 = s3.executeQuery("select totalNum from subline where subNo = '"+subNoTrail[k - 1]+"'");
	    			   Statement s4 = conn.createStatement();
	    			   ResultSet rset3 = s4.executeQuery("select staNo from station where staName='"+staNameRoute.get(staNameRoute.size() - 1)+"'and subNo = '"+subNoTrail[k - 1]+"'");
	    			   if(rset2.next())
	    			   {
	    				   total=rset2.getInt("totalNum");
	    			       
	    			   }
	    		
	    			   if(rset3.next())
	    			      starNo=rset3.getInt("staNo");
	    			  
	    			   s3.close();
	    			   s4.close();
	    			   }catch(SQLException sql){}
					   
					   int way1=0,way2=0;
					   if(staNum.get(0)>staNum.get(1))
                      {
                           if(starNo<staNum.get(1))
                           {
                           	way1=staNum.get(1)-starNo;
                           	way2=starNo+(total-staNum.get(0));
                           	if(way1>way2)
                           		staNameRoute.add(transferStaName.get(0));
                           	else 
                           		staNameRoute.add(transferStaName.get(1));
                           }
                           else if(starNo>staNum.get(0))
                           {
                           	way1=starNo-staNum.get(0);
                           	way2=starNo+staNum.get(1);
                           	if(way1>way2)
                                  staNameRoute.add(transferStaName.get(1));
                           	else
                           	   staNameRoute.add(transferStaName.get(0));
                           }
                           else
                           {
                           	way1=Math.abs(staNum.get(1)-starNo);
                           	way2=Math.abs(staNum.get(0)-starNo);
                           	if(way1>way2)
                           		staNameRoute.add(transferStaName.get(0));
                           	else
                           		staNameRoute.add(transferStaName.get(1));
                           		
                           }
                           
                      }
					   else
					   {
						   if(starNo<staNum.get(0))
                          {
                          	way1=staNum.get(0)-starNo;
                          	way2=starNo+(total-staNum.get(1));
                          	if(way1>way2)
                          		staNameRoute.add(transferStaName.get(1));
                          	else 
                          		staNameRoute.add(transferStaName.get(0));
                          }
                          else if(starNo>staNum.get(1))
                          {
                          	way1=starNo-staNum.get(1);
                          	way2=starNo+staNum.get(0);
                          	if(way1>way2)
                                 staNameRoute.add(transferStaName.get(0));
                          	else
                          	   staNameRoute.add(transferStaName.get(1));
                          }
                          else
                          {
                          	way1=Math.abs(staNum.get(1)-starNo);
                          	way2=Math.abs(staNum.get(0)-starNo);
                          	if(way1>way2)
                          		staNameRoute.add(transferStaName.get(0));
                          	else
                          		staNameRoute.add(transferStaName.get(1));
                          		
                          }
					   }
		    		 
		        }
		    }
			  else
				  staNameRoute.add(transferStaName.get(0));
			
	    }
	    if(num == 2 && subNoTrail[0] != subNoTrail[1])
	    {
	    	staNum = new ArrayList<Integer>();
	    	transferStaName = new ArrayList<String>();
	    	try{
				Statement s1 = conn.createStatement(); 
				ResultSet rset = s1.executeQuery(
			"select staName,staNo from station where staName in "
			+ "(select staName FROM station "
			+ "WHERE subNo = '"+subNoTrail[1]+"') and subNo = '"+subNoTrail[0]+"'");
			//找出集合中与两条不同地铁线间的中转站(1-2个).
				while(rset.next())
				{
					transferStaName.add(rset.getString("staName"));
					staNum.add(rset.getInt("staNo"));
					
				}		
		       }catch(SQLException sqle){System.out.println("error");}
	    	//int p = 0, q = 0;
	    	
			  if(transferStaName.size() == 2)//换乘站有两条线
		    	{
				  
				   if(subNoTrail[0] != 2 && subNoTrail[0] != 10)
					   //若不等于2号线或10号线.
				   {
					  
					   try{
						  
		    			   Statement s2 = conn.createStatement();
		    			   ResultSet rset = s2.executeQuery(
		    					   "select staNo from station "
		    					   + "where subNo = '"+subNoTrail[0]+"' and staName = '"+staNameRoute.get(0)+"'");
		    			  if(rset.next())
		    			  {
		    				  if((Math.abs(rset.getInt("staNo") - staNum.get(0))) > (Math.abs(rset.getInt("staNo") - staNum.get(1))))
		 		            staNameRoute.add(transferStaName.get(1));
		 		              else
		 		        	staNameRoute.add(transferStaName.get(0));
		    			  }
		    			
		 		        
		                 }catch(SQLException sql){}
				   }
				   else
				   {     
                     int total=0,starNo=0;
					 try{
				        Statement s3 = conn.createStatement();
	    			   ResultSet rset2 = s3.executeQuery("select totalNum from subline where subNo = '"+subNoTrail[0]+"'");
	    			   Statement s4 = conn.createStatement();
	    			   ResultSet rset3 = s4.executeQuery("select staNo from station where staName='"+staNameRoute.get(0)+"'and subNo = '"+subNoTrail[0]+"'");
	    			   if(rset2.next())
	    			   {
	    				   total=rset2.getInt("totalNum");
	    			       
	    			   }
	    			     
	    			     
	    			   if(rset3.next())
	    			      starNo=rset3.getInt("staNo");
	    			   
	    			   
	    			   }catch(SQLException sql){}
					   int way1=0,way2=0;
					   if(staNum.get(0)>staNum.get(1))
                       {
                            if(starNo<staNum.get(1))
                            {
                            	way1=staNum.get(1)-starNo;
                            	way2=starNo+(total-staNum.get(0));
                            	if(way1>way2)
                            		staNameRoute.add(transferStaName.get(0));
                            	else 
                            		staNameRoute.add(transferStaName.get(1));
                            }
                            else if(starNo>staNum.get(0))
                            {
                            	way1=starNo-staNum.get(0);
                            	way2=starNo+staNum.get(1);
                            	if(way1>way2)
                                   staNameRoute.add(transferStaName.get(1));
                            	else
                            	   staNameRoute.add(transferStaName.get(0));
                            }
                            else
                            {
                            	way1=Math.abs(staNum.get(1)-starNo);
                            	way2=Math.abs(staNum.get(0)-starNo);
                            	if(way1>way2)
                            		staNameRoute.add(transferStaName.get(0));
                            	else
                            		staNameRoute.add(transferStaName.get(1));
                            		
                            }
                            
                       }
					   else
					   {
						   if(starNo<staNum.get(0))
                           {
                           	way1=staNum.get(0)-starNo;
                           	way2=starNo+(total-staNum.get(1));
                           	if(way1>way2)
                           		staNameRoute.add(transferStaName.get(1));
                           	else 
                           		staNameRoute.add(transferStaName.get(0));
                           }
                           else if(starNo>staNum.get(1))
                           {
                           	way1=starNo-staNum.get(1);
                           	way2=starNo+staNum.get(0);
                           	if(way1>way2)
                                  staNameRoute.add(transferStaName.get(0));
                           	else
                           	   staNameRoute.add(transferStaName.get(1));
                           }
                           else
                           {
                           	way1=Math.abs(staNum.get(1)-starNo);
                           	way2=Math.abs(staNum.get(0)-starNo);
                           	if(way1>way2)
                           		staNameRoute.add(transferStaName.get(0));
                           	else
                           		staNameRoute.add(transferStaName.get(1));
                           		
                           }
					   }
					 
					
					 
				  }
			  
	           }
			  else
				  staNameRoute.add(transferStaName.get(0));
	    }
	    staNameRoute.add(terminalStation);
	    int flag = 0;
		for (int t = 0; t < staNameRoute.size() - 1; t++)
    		{
			     // System.out.println(staNameRoute.get(t));
			      if(t <= num)
			      {
			    	  foundDis(staNameRoute.get(t), staNameRoute.get(t + 1), subNoTrail[t], conn, FLAG,t);
			    	  flag = subNoTrail[t];
			      }
			          
			      else
			    	  foundDis(staNameRoute.get(t), staNameRoute.get(t + 1), flag, conn,FLAG,t);
              if(FLAG == 1)
			        totalDisArray[t] = totalDis;
			      
    		}
		
		
	}
	public void foundDis(String exchangeStation1, String exchangeStation2, int line, Connection conn, int FLAG, int t)
	{
		nextDis = new ArrayList<Integer>();
		try{
			int F=0;
			int S=0;
			int temp=0;
			Statement s1 = conn.createStatement();
			ResultSet rset = s1.executeQuery("select staNo from station where staName='"+exchangeStation1+"' and subNo='"+line+"'" );
			   if(rset.next())
				 F=rset.getInt("staNo");
			Statement s2 = conn.createStatement();
			ResultSet rset1 = s2.executeQuery("select staNo from station where staName='"+exchangeStation2+"' and subNo='"+line+"'" );
			   if(rset1.next())
				   S=rset1.getInt("staNo");
			if(F > S)
			{
				temp=S;
				S=F;
				F=temp;
			}
			Statement s3 = conn.createStatement();
			ResultSet rset2 = s3.executeQuery("select nextDis from station where staNo between '"+F+"' and '"+(S-1)+"' and subNo='"+line+"'");
			while(rset2.next())
			{
				nextDis.add(rset2.getInt("nextDis"));
				if(FLAG == 1)
					totalStation++;
			}
			if(FLAG == 1)
				stationPassNum[t]=totalStation;
		}catch(SQLException sqle){}
		
		for(int l = 0; l < nextDis.size(); l++)
			totalDis += nextDis.get(l);
        

		nextDis = null;
	}
	public static void main(String args[]){
	  
	  DBHelp test = new DBHelp();
	  Connection conn = null;
	  conn = test.getConnection();  //建立数据库连接.
	  test.placeStation(conn);   //将景点转化为最近的地铁站.
	  test.query(conn);        //找出地铁站所在的地铁线.
	  
	  /*for(int i = 0; i < test.tc.length; i++)
		  System.out.print(" "+test.tc[i]);  */
	  test.ChangeSubNo(conn);
	  test.chooseRoute(conn, test.RealSubNoTrail, 1);
        for(int i = 0; i < test.staNameRoute.size(); i++)
		  { 
        	System.out.println();
        	System.out.println(test.staNameRoute.get(i));
        	System.out.println("   |   ");
        	System.out.println("   |   ");
        	if(test.RealSubNoTrail[i]!=0)
        	{if(test.RealSubNoTrail[i] == 3)
        		System.out.println("Airport line");
             else if(test.RealSubNoTrail[i] == 14)
            	 System.out.println("Batong line");
             else if(test.RealSubNoTrail[i] == 11)
            	 System.out.println("Yizhuang line");
             else if(test.RealSubNoTrail[i] == 16)
            	 System.out.println("Changping line");
        	 else         	
        		System.out.println(test.RealSubNoTrail[i]+" line");
        	}
        	System.out.println("   |   ");
        	System.out.println("   |   ");
        	if(test.totalDisArray[i]!=0)
        	{
        		System.out.println("totalSta  = "+ test.stationPassNum[i]);
        	    System.out.println("totalDis = " + test.totalDisArray[i]+" m");
        	}
        	
            }
        System.out.println("Total station number is " + test.totalStation);
	  System.out.println();
	  
	  try{
		  conn.close();
	  }catch(SQLException sqle){}

	   
   }
}
