package com.example;

// Go-Pro Selection
// Janet Weber   DUE 11/2/2015

// Assignment:
// Design a class to allow youto select yor new GoPro camera based on  your application.
// So regardless of if you are recording your granddaughter's first bike ride or base
// jumping off cliffs in Argentina, your program will allow you to select the right
// camera for your needs.

import java.util.Scanner;

public class Camera {
    int category;               // entry level => 1, performance => 2
    boolean wifi;               // wifi required?
    boolean touch;              // touch display required?
    boolean UHSPC;              // Ultra High Speed Photo Capture required (12MP/30fps)?
    boolean slowMo;             // Slow Motion required (1080p120)?
    boolean UIQ;                // Ultimate Image Quality required (4K30)?

    public Camera (){           // constructor - initializes all fields by
        this(1);                //    using constructor below
    }

    public Camera (int cat){     // constructor - initializes all fields
        this.category = cat;
        this.wifi = false;
        this.touch = false;
        this.UHSPC = false;
        this.slowMo = false;
        this.UIQ = false;
    }

    // this method sets the category 1 for entry-level, 2 for performance
    public void setCategory(){
        Scanner userInput = new Scanner(System.in);    // declare/initialize variable userInput to take console input
        int level = 0;                                 // declare/initialize variable level to 0 (invalid)
        while (!((level == 1)||(level == 2))){         // Loop until a valid input is received (1 or 2)
            System.out.println("Camera Type: ");       //                   display prompts
            System.out.println("     Enter 1 for Entry Level");
            System.out.print("     Enter 2 for Performance       => ");
            level = userInput.nextInt();               //                   get input
        }
        this.category = level;                         // valid input received - set the category
    } // end of setCategory() method

    // this method gets the value for the remaining attributes
    public Boolean setAttribute(String attribute) {
        String answer = "";                            // declare/initialize variable answer (invalid)
        Scanner userInput = new Scanner(System.in);    // declare/initialize variable userInput to take console input

        while (!((answer.equals("Y")) || (answer.equals("N")))) {   // Loop until a valid input is received (Y or N)
            System.out.print(attribute + " required? (Y/N) => ");   //     display prompt using attribute parameter
            answer = userInput.next();                              //     get input
        }
        if (answer.equals("Y")) return(true);                       // valid input received return appropriate boolean
        else return(false);
    } // end of getAttribute() method

    // This method performs most of the work.  It calls the above methods and processes through
    // attributes recommending a GoPro model.
    public String getModel() {
        this.setCategory();                         // set the category
        if (this.category == 1){                    // camera type is entry level
            this.wifi = this.setAttribute("WiFi "); //       Is wifi required?
            if (this.wifi) {                        //          Y then
                this.touch = this.setAttribute("Touch Display");    // Is Touch Display required
                if (this.touch) return ("HERO+ LCD");               //   Y - HERO+ LCD
                        else return("HERO+");                       //   N - HERO+
            } else return ("HERO");                //           N - HERO
        } else {                                   // camera type is performance
            this.wifi = true;                      // all performance cameras have wifi
            this.touch = this.setAttribute("Touch Display"); // Is Touch Display required?
            this.UHSPC = this.setAttribute("Ultra High Speed Photo Capture (12MP/30fps)"); // Is ultra high speed photo capture required?
            if ((!this.touch)&&(!this.UHSPC)) return("HERO4 Session");  // BOTH touch and UHSPC - HERO4Session
            else {                                                      // Otherwise -
                this.UIQ = this.setAttribute("Ultimate Image Quality (4K30)"); // Is ultimate image quality required?
                this.slowMo = this.setAttribute("Slow Motion (1080p120)");     // Is slow motion required?
                if ((this.UIQ)||(this.slowMo)) return ("HERO4 Black");         // Either UIQ and slowMo - "HERO4 Black
                else return("HERO4 Silver");                                   // Otherwise (neither)// - HERO4 Silver
            }
        }
    }  // end of getModel() method

    // The main method: instantiates a camera object and gets the model
    public static void main(String[] args) {
        String model;                                               // variable holding model type

        System.out.println("Which GoPro is right for you! \n");     // Display purpose
        Camera janetsCamera = new Camera();                         // instantiate a camera object called janetsCamera
        model = janetsCamera.getModel();                            // use class definition to get the model
        System.out.println("\nThe recommended GoPro model for you is => " + model); // display selected model to console
    } // end of main method
} // end of class Camera
