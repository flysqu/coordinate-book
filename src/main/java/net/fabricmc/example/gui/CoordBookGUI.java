package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.widget.*;
import net.fabricmc.example.json.write;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;

import net.minecraft.text.Text;


import net.fabricmc.example.json.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CoordBookGUI extends LightweightGuiDescription {
    public CoordBookGUI() {


        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(306, 210);

        WLabel label = new WLabel(Text.literal("  Cordinate Book!"));
        root.add(label, 6,1);

        // /Row 1
        WTextField field1 = new WTextField();
        root.add(field1, 1,2, 6,1);

        WTextField field2 = new WTextField();
        root.add(field2, 1,4, 6, 1);

        WTextField field3 = new WTextField();
        root.add(field3, 1,6, 6,1);

        WTextField field4 = new WTextField();
        root.add(field4, 1,8, 6,1);
        // \Row 1

        // /Row 2
        WTextField field5 = new WTextField();
        root.add(field5, 10,2, 6,1);

        WTextField field6 = new WTextField();
        root.add(field6, 10,4, 6,1);

        WTextField field7 = new WTextField();
        root.add(field7, 10,6, 6,1);

        WTextField field8 = new WTextField();
        root.add(field8, 10,8, 6, 1);

        // Buttons!
        WButton save_and_exit = new WButton(Text.translatable("Save And Exit"));
        save_and_exit.setOnClick(() -> {
            write writefuncts = new write();
            writefuncts.writefunct(field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText());
        });
        root.add(save_and_exit, 6, 10, 5, 1);

        WButton revert = new WButton(Text.translatable("Revert"));
        revert.setOnClick(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("config//coords.json"))) {

                // Set coordinates to text fields
                field1.setText(read.readfunct(4));
                field2.setText(read.readfunct(5));
                field3.setText(read.readfunct(6));
                field4.setText(read.readfunct(7));
                field5.setText(read.readfunct(0));
                field6.setText(read.readfunct(1));
                field7.setText(read.readfunct(2));
                field8.setText(read.readfunct(3));

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.add(revert , 1, 10, 3, 1);
    }
}


