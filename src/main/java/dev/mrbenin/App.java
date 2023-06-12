package dev.mrbenin;

import java.io.File;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App 
{
    public static void main( String[] args )
    {

       if(args.length != 2){
           System.out.println("Informe o caminho do diretório e o arquivo contendo a regex");
           System.exit(0);
       } 

       String path = "/mnt/d/temp/invencivel";
       File dir = new File(path);

       TreeMap<String,String> fileMap = new TreeMap<String,String>(); 
       String REGEX = ".*-\\s*(.*?)\\s*-.*\\((\\d+)\\).*";
       Pattern pattern = Pattern.compile(REGEX);
      
        int arquivosConvertidos = 0;
       if(dir.isDirectory()){
           File[] files = dir.listFiles();
           
           for(File file : files){
                Matcher matcher = pattern.matcher(file.getName());
                if(matcher.matches()){
                    String newName = matcher.group(1) + " - #" + matcher.group(2) + "." + file.getName().substring(file.getName().lastIndexOf(".")+1);
                    fileMap.put(file.getName(), newName);
                    if(!new File(path +"/renamed").exists()){
                        new File(path +"/renamed").mkdir();
                    }
                    if(file.renameTo(new File(path +"/renamed/"+newName))){
                        System.out.println("Old: " + file.getName() + " -->" + " New: " + newName);
                        arquivosConvertidos++;
                    }
                }
           }
       }
       System.out.println("Arquivos convertidos: " + arquivosConvertidos);
    }
}
