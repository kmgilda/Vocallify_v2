package trollers.com.vocallify_v2;

import java.util.Random;
import android.util.Log;

public class QuestionRadio{

    public WordsObject array[];
    public int id;
    public String OptA;
    public String OptB;
    public String OptC;
    public String OptD;
    public String ANSWER;
    boolean flag2=false;        //To determine if this new option can be the answer or not


    private static final String TAG ="FirstTimetag";

    public QuestionRadio(WordsObject arr[],int ID)
    {
        array=arr;
        id=ID;
        OptA = "-9";
        OptB = "-9";
        OptC = "-9";
        OptD = "-9";
    }

    public QuestionRadio getQues()
    {
        Random rand = new Random();
        boolean flag1;              //To determine if we can have "NONE OF THE ABOVE" as an option
        String answernot="";
        flag1 = false;
        flag2 = false;

        flag1 = ((rand.nextInt())%2==0) ? true : false;

        if(flag1 == true) {
            OptD = "NONE OF THE ABOVE";
            flag2 = ((rand.nextInt())%2==0) ? true : false;
            if (flag2 == true) {
                ANSWER = "NONE OF THE ABOVE";
                answernot = array[id].meaning;
            }
            else
                ANSWER = array[id].meaning;
        }

        if(flag1==true) {                               //Check if Option D is of NOTA type
            Log.i(TAG,"Option D is of NOTA type and flag1 is "+flag1+" and flag2 is "+flag2);

            if ((ANSWER.equals("NONE OF THE ABOVE"))) {                 //Check if answer is NOTA type
                Log.i(TAG,"Answer is NOTA");

                int idtemp;
                if (OptA.equals("-9")) {
                    while(true) {
                        idtemp = rand.nextInt() % 5;
                        if (idtemp < 0)
                            idtemp *= -1;
                        if(array[idtemp].meaning.equals(answernot))
                            continue;
                        else {
                            OptA = array[idtemp].meaning;
                            break;
                        }
                    }
                }

                while (true) {
                    idtemp = rand.nextInt() % 5;
                    if (idtemp < 0)
                        idtemp *= -1;
                    if(array[idtemp].meaning.equals(answernot))
                        continue;
                    if (OptA.equals(array[idtemp].meaning))
                        continue;
                    else {
                        if (OptB.equals("-9"))
                            OptB = array[idtemp].meaning;          //option b done
                            break;
                    }
                }

                while (true) {
                    idtemp = rand.nextInt() % 5;
                    if (idtemp < 0)
                        idtemp *= -1;
                    if(array[idtemp].meaning.equals(answernot))
                        continue;
                    if ((OptA.equals(array[idtemp].meaning)) || (OptB.equals(array[idtemp].meaning)))
                        continue;
                    else {
                        if (OptC.equals("-9"))
                            OptC = array[idtemp].meaning;          //option c done
                        break;
                    }
                }
            }
            else
            {
                Log.i(TAG,"option d of nota type is not the answer");
                int idtemp;int tempid;
                idtemp = rand.nextInt() % 3;
                if(idtemp<0)
                    idtemp*=-1;
                tempid=idtemp;
                if(idtemp==0)
                    OptA = ANSWER;
                else if(idtemp==1)
                    OptB = ANSWER;
                else if(idtemp==2)
                    OptC = ANSWER;
                answernot = ANSWER;

                Log.i(TAG,""+tempid);

                if(OptA.equals("-9"))
                {
                    Log.i(TAG,""+"First A is getting executed");
                    while(true) {
                        idtemp = rand.nextInt() % 5;
                        if (idtemp < 0)
                            idtemp *= -1;
                        if((array[idtemp].meaning).equals(answernot))
                            continue;
                        else {
                            OptA = array[idtemp].meaning;
                            break;
                        }
                    }
                }
                Log.i(TAG,""+"A is now "+OptA);
                if(OptB.equals("-9"))
                {
                    Log.i(TAG,""+"Now B is getting executed");
                    while(true){
                        idtemp = rand.nextInt() % 5;
                        if(idtemp<0)
                            idtemp*=-1;
                        if((array[idtemp].meaning).equals(answernot))
                            continue;
                        if(OptA.equals(array[idtemp].meaning))
                            continue;
                        else
                        {
                            OptB = array[idtemp].meaning;       //Option B done
                            break;
                        }
                    }
                }
                Log.i(TAG,""+"Opt B is "+OptB);
                if(OptC.equals("-9"))
                {
                    Log.i(TAG,""+"Now C is getting executed");
                    while(true){
                        idtemp = rand.nextInt() % 5;
                        if(idtemp<0)
                            idtemp*=-1;
                        if((array[idtemp].meaning).equals(answernot))
                            continue;
                        if((OptA.equals(array[idtemp].meaning))||(OptB.equals(array[idtemp].meaning)))
                            continue;
                        else
                        {
                            OptC = array[idtemp].meaning;       //Option C done
                            break;
                        }
                    }

                }

            }
        }
        else                                            //repeating entire procedure when no NOTA option available
        {
            Log.i(TAG,"No option d as nota type and flag1 is "+flag1+" and flag2 is "+flag2);
            ANSWER = array[id].meaning;
            int idtemp;int tempid;
            idtemp = rand.nextInt() % 4;
            if(idtemp<0)
                idtemp*=-1;
            tempid=idtemp;
            if(idtemp==0)
                OptA = ANSWER;
            else if(idtemp==1)
                OptB = ANSWER;
            else if(idtemp==2)
                OptC = ANSWER;
            else if(idtemp==3)
                OptD = ANSWER;
            answernot = ANSWER;

            Log.i(TAG,""+tempid);

            if(OptA.equals("-9"))
            {
                Log.i(TAG,""+"First A is getting executed");
                while(true) {
                    idtemp = rand.nextInt() % 5;
                    if (idtemp < 0)
                        idtemp *= -1;
                    if((array[idtemp].meaning).equals(answernot))
                        continue;
                    else {
                        OptA = array[idtemp].meaning;
                        break;
                    }
                }
            }
            Log.i(TAG,""+"A is now "+OptA);
            if(OptB.equals("-9"))
            {
                Log.i(TAG,""+"Now B is getting executed");
                while(true){
                    idtemp = rand.nextInt() % 5;
                    if(idtemp<0)
                        idtemp*=-1;
                    if((array[idtemp].meaning).equals(answernot))
                        continue;
                    if(OptA.equals(array[idtemp].meaning))
                        continue;
                    else
                    {
                        OptB = array[idtemp].meaning;       //Option B done
                        break;
                    }
                }
            }
            Log.i(TAG,""+"Opt B is "+OptB);
            if(OptC.equals("-9"))
            {
                Log.i(TAG,""+"Now C is getting executed");
                while(true){
                    idtemp = rand.nextInt() % 5;
                    if(idtemp<0)
                        idtemp*=-1;
                    if((array[idtemp].meaning).equals(answernot))
                        continue;
                    if((OptA.equals(array[idtemp].meaning))||(OptB.equals(array[idtemp].meaning)))
                        continue;
                    else
                    {
                        OptC = array[idtemp].meaning;       //Option C done
                        break;
                    }
                }

            }
            Log.i(TAG,""+"C is now "+OptC);
            if(OptD.equals("-9"))
            {
                Log.i(TAG,"Now D is getting executed");
                while(true){
                    idtemp = rand.nextInt() % 5;
                    if(idtemp<0)
                        idtemp*=-1;
                    if((array[idtemp].meaning).equals(answernot))
                        continue;
                    if((OptA.equals(array[idtemp].meaning))||(OptB.equals(array[idtemp].meaning))
                            ||(OptC.equals(array[idtemp].meaning)))
                        continue;
                    else
                    {
                        OptD = array[idtemp].meaning;       //Option D done
                        break;
                    }
                }
            }
            Log.i(TAG,""+"D is now"+OptD);
        }
        return this;
    }

}
