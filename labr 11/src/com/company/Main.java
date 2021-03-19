package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        int m =1;
        int n=0;
        String[] name = new String[30];
        int[] groupn = new int[30];
        float[] avg_balls1 = new float[30];
        float[] avg_balls2 = new float[30];
        int[] id1= new int[30];
        Students student= new Students();
        Scanner scanner = new Scanner(System.in);
        Students[] student1 = new Students[m];
        try
        {
            System.out.print("vvedite kol-vo studentov:");
            n = scanner.nextInt();
            if(n>30){
                throw new Exception("Число х должно быть меньше 30");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }


        for(int i=0;i < n;i++)// заполнение класса Students
        {

            String q = scanner.nextLine();
            System.out.print("Vvedite familiu:");
            name[i] = scanner.nextLine();
            System.out.print("Vvedite nomer grupi:");
            groupn[i] = scanner.nextInt();

            System.out.print("Vvedite srednii ball za 1 exzamen:");
            avg_balls1[i] = scanner.nextFloat();
            System.out.print("Vvedite srednii ball za 2 exzamen:");
            avg_balls2[i] = scanner.nextFloat();
            try {
                System.out.print("Vvedire nomer zachetki:");
                id1[i] = scanner.nextInt();
            } catch (InputMismatchException e ){
                scanner.next();
                System.out.println("ввели не число");

            }


        }
        Students students1= new Students(n,name,groupn,avg_balls1,avg_balls2,id1);



        System.out.println("Do sortirovki:");
        students1.outPut(n);//вывод списка студентов
        students1.sort(n);//сортировка студентов
        System.out.println("posle sortirovki:");
        students1.outPut(n);//вывод списка студентов
        students1.zadanie(n);//задание
        students1.sumofballs(n);//сумма баллов

        students1.setS(n);// выбор студентов для начисления стипендии
        students1.out_stipendia(n);//вывод стипендии студентов


        String q = scanner.nextLine();
        for(int i=0;i<m;i++) {// заполнение массива объектов
            System.out.println("Введите фамлию студента:");
            student1[i] = new Students();
            student1[i].st(q = scanner.nextLine());
        }
        for(int i = 0;i< m;i++)//вывод (массив объектов)
        {
            System.out.println(student1[i].name);

        }
        for(int i = 0; i<m;i++)// Работа со строками (длина строки)
        {

            System.out.println("Длинна фамилии:"+ student1[i].name.length());

        }

        Students[][] ob = new Students[n][2];

        for(int i=0;i<n;i++){
            for(int j =0;j<2;j++){

                ob[i][j] = new Students();

            }
        }


        for(int i =0;i<n;i++){

            System.out.println("Введите фамлию студента:");
            ob[i][0].st1(scanner.nextLine(),i);

            System.out.print("Vvedite srednii ball za 1 exzamen:");
            float a = scanner.nextFloat();
            ob[i][1].avg_ball1(a,i);
            if(i%2 == 0){

                String q1 = scanner.nextLine();

            }

        }
        for(int i =0;i<n;i++){

          System.out.println("Студент:"+ ob[i][0].get_last_name(i) + "Оценка за 1 экзамен:"+ ob[i][1].get_avg1(i));


        }

    }



}


class Students {

    String name;
    private  static int stipendia = 2600;
    private String[] last_name = new String[30];
    private static int [] fiplata = new int[30];
    private int[] group = new int[30];
    private float[] avg_ball1 = new float[30];
    private float[] avg_ball2 = new float[30];
    Students_book student_book ;

    Students(){// конструктор без параметров


    }

    Students(int n,String[] last_name, int[] group, float[] avg_ball1, float[] avg_ball2,int[] id1){// конструктор с параметрами
        for(int i = 0;i < n; i++){
            this.last_name[i] = last_name[i];
            this.group[i] = group[i];
            this.avg_ball1[i] = avg_ball1[i];
            this.avg_ball2[i] = avg_ball2[i];

        }
        student_book = new Students_book(id1);
    }



    public  static void setStipendia(int i,int k){// установка стипендии

        fiplata[i] = k;

    }

    public   void setS(int n){// выбор студентов по оценкам

        for(int i =0;i<n;i++){

            if((avg_ball1[i]+avg_ball2[i])/2 >= 4){

                Students.setStipendia(i, Students.stipendia);


            }
            if((avg_ball1[i]+avg_ball2[i])/2 < 4 ){

                Students.setStipendia(i,0);


            }

        }


    }

    public void out_stipendia(int n){// для статического поля и метода (вывод)

        for(int i =0;i<n;i++){

            System.out.println("Студент:"+ last_name[i]+"Стипендия:"+ Students.fiplata[i]);

        }

    }


    public void st(String name){// сеттер для массива структур

        this.name = name;

    }
    public void st1(String name,int i){// сеттер для массива структур

        last_name[i] = name;

    }

    public void avg_ball1(float x,int i){

        avg_ball1[i] = x;

    }
    public String get_last_name(int i){

        return last_name[i] ;

    }

    public float get_avg1(int i){

        return  avg_ball1[i];

    }



    public void outPut(int n){//вывод списка студентов

        for(int i = 0;i<n;i++)
        {

            System.out.println(last_name[i]+"|" + group[i]+ "|" + avg_ball1[i] + "|" + avg_ball2[i] + "|" + student_book.get_id(i));

        }



    }
    public void sort(int n){// сортировка
        String ln;
        int gn;
        float ab1;
        float ab2;
        int ID,ID1,ID2;
        for(int i = 0; i < n-1; i++)
        {
            for(int j = n-1; j > i; j--)
            {
                if(group[j-1] < group[j])
                {
                    ln = last_name[j-1];
                    last_name[j-1] = last_name[j];
                    last_name[j] = ln;

                    gn = group[j-1];
                    group[j-1] = group[j];
                    group[j] = gn;

                    ab1 = avg_ball1[j-1];
                    avg_ball1[j-1] = avg_ball1[j];
                    avg_ball1[j] = ab1;

                    ab2 = avg_ball2[j-1];
                    avg_ball2[j-1] = avg_ball2[j];
                    avg_ball2[j] = ab2;

                    ID = student_book.get_id(j-1);
                    ID1 = student_book.get_id(j);
                    student_book.set_id(j-1,ID1);
                    student_book.set_id(j,ID);
                }
            }
        }


    }

    public void zadanie(int n){//поиск студентов по заданию
        System.out.println("Studenti, u kotorix ocenka za 2 examen ni>|<e , chem za 1:");
        for(int i = 0; i < n; i++)
        {
            if(avg_ball2[i]<avg_ball1[i])
            {
                System.out.println(last_name[i]);

            }
        }



    }

    public void sumofballs(int n){// сумма баллов
        float sum1 = 0;
        float sum2 = 0;
        for(int i = 0; i< n; i++){

            sum1+=avg_ball1[i];

            sum2+= avg_ball2[i];

        }
        System.out.printf("Summa za 1 examen: %f \n",sum1);
        System.out.printf("Summa za 2 examen: %f \n",sum2);

    }

    class Students_book{

        private int[]  id = new int [30];

        Students_book(){


        }

        Students_book(int[] id){
            int n = id.length;
            for(int i =0;i<n;i++) {
                this.id[i] = id[i];
            }
        }


        public void  set_id(int i,int id1){//сеттер

            id[i] = id1;

        }


        public int get_id(int i){//геттер

            return id[i];

        }



    }

}

