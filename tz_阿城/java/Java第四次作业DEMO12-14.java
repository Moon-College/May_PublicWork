import java.util.Scanner;

public class  Demo1
{
	public static void main(String[] args) 
	{
		int i=0;
		while(i<20)
		{
		    System.out.println("Hello World!");
			i++;
		}
	}
} 

class  Demo2
{
	public static void main(String[] args) 
	{
		int i=0;
		do
		{
		    System.out.println(i+"Hello World!");
			i++;
		}
		while(i<20);

	}
}

class  Demo3
{
	public static void main(String[] args) 
	{
        for(int i = 0 ;i<10; i++)
		{
		    System.out.println(i+"Hello World!");
		}


	}
}

class  Demo4
{
	public static void main(String[] args) 
	{
        for(System.out.println("Hello World!"); ; System.out.println("Hello World2!"))
		{
		   
		}


	}
}
class  Demo5
{
	public static void main(String[] args) 
	{
        for(int num =0 ; num<10 ; num++)
		{
		   System.out.println("Hello World2!");
		}
  //     System.out.println(num);会编译报错

	}
}

class  Demo6
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int count = 0;
        for(int num =1 ; num<=10 ; num++)
		{
		   System.out.println("请输入第I个整数");
		   int value = scan.nextInt();
		   if(value%2==0)
		   {
              count++;
		   }
            
		}
       System.out.println("所有数据"+count);

	}
}

class  Demo8
{
	public static void main(String[] args) 
	{
       for(int i =0;i<3;i++)
	   {
		   String str ="";
           for(int j=0;j<4;j++)
		   {
              str = "*";
		   }
		   System.out.println(str);
		   /*
           for(j=0;j<4;j++)
		   {
              System.out.print("*");
		   }
		   System.out.println(str);
		   */
	   }


	}
}


class  Demo9
{
	public static void main(String[] args) 
	{
       for(int i =0;i<5;i++)
	   {

           for(int j=0;j<=i;j++)
		   {
              System.out.print("*");
		   }
		   System.out.println();
		   
	   }


	}
}

class  Demo10
{
	public static void main(String[] args) 
	{
       for(int i =1;i<=4;i++)
	   {

           for(int j=1;j<=2*i-1;j++)
		   {
                System.out.print("*");
		   }
		   System.out.println();
		   
	   }


	}
}


class  Demo11
{
	public static void main(String[] args) 
	{
	   int num = 7;
       for(int i =1;i<=num;i++)
	   {

           for(int j=1;j<=num-i;j++)
		   {
                System.out.print(" ");
		   }
		    for(int k=1;k<=2*i-1;k++)
		   {
                System.out.print("*");
		   }
		   System.out.println();
		   
	   }


	}
}

class  Demo12
{
	public static void main(String[] args) 
	{
	   int num = 7;
       for(int i =1;i<=num;i++)
	   {
           if(i<=num/2+1)
		   {
               for(int j=1;j<=2*i-1;j++)
		       {
                    System.out.print("*");
		       }
		   }
		   else
		   {
               for(int j=1;j<=(num-i+1)*2-1;j++)
		       {
                    System.out.print("*");
		       }              
		   }

		   System.out.println();
		   
	   }


	}
}

class  Demo13
{
	public static void main(String[] args) 
	{
	   int num = 7;

       for(int i =1;i<=num;i++)
	   {
           if(i<=num/2+1)
		   {
               for(int j=1;j<=num/2+1-i;j++)
		       {
                    System.out.print(" ");
		       }
		        for(int k=1;k<=2*i-1;k++)
		       {
                    System.out.print("*");
		       }
		   }
		   else
		   {
               for(int j=1;j<=i-num/2-1;j++)
		       {
                    System.out.print(" ");
		       }
		        for(int k=1;k<=(num-i)*2+1;k++)
		       {
                    System.out.print("*");
		       }
		   }
		   System.out.println();
		   
	   }


	}
}

class  Demo14 
{
	public static void main(String[] args) 
	{
	   int num = 9;

       for(int i =1;i<=num;i++)
	   {
           String str = "";
           for(int j=1;j<=i;j++)
		   {
              System.out.print(i+"*"+j+" = " +i*j +" " );   
                
		   }

		   System.out.println();
		   
	   }


	}
}
