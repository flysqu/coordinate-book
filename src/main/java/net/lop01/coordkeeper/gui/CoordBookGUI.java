package net.lop01.coordkeeper.gui;

import io.github.cottonmc.cotton.gui.widget.*;
import net.lop01.coordkeeper.json.write;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;

import net.minecraft.text.Text;

import net.lop01.coordkeeper.json.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class CoordBookGUI extends LightweightGuiDescription {
    public CoordBookGUI() {
        AtomicInteger pagenum = new AtomicInteger(1);

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(306, 240);

        WLabel label = new WLabel(Text.literal("  Cordinate Book!"));
        root.add(label, 6,1);

        WTextField pagename = new WTextField();
        pagename.setText("Page Name");
        root.add(pagename, 6, 2, 5, 1);

        // /Row 1
        WTextField field1 = new WTextField();
        root.add(field1, 1,4, 7,1);

        WTextField field2 = new WTextField();
        root.add(field2, 1,6, 7, 1);

        WTextField field3 = new WTextField();
        root.add(field3, 1,8, 7,1);

        WTextField field4 = new WTextField();
        root.add(field4, 1,10, 7,1);
        // \Row 1

        // /Row 2
        WTextField field5 = new WTextField();
        root.add(field5, 9,4, 7,1);

        WTextField field6 = new WTextField();
        root.add(field6, 9,6, 7,1);

        WTextField field7 = new WTextField();
        root.add(field7, 9,8, 7,1);

        WTextField field8 = new WTextField();
        root.add(field8, 9,10, 7, 1);
        // \Row 2

        // Buttons!
        WButton save_and_exit = new WButton(Text.translatable("Save"));
        save_and_exit.setOnClick(() -> {
            write writefuncts = new write();
            writefuncts.writeFunctWithGson(pagenum.get(), pagename.getText(), field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText());
        });
        root.add(save_and_exit, 10, 12, 4, 1);

        WLabel pagenumlabel = new WLabel(Text.translatable("  Page: " + pagenum.toString()));
        root.add(pagenumlabel, 3,1);

        WButton revert = new WButton(Text.translatable("Revert To Save"));
        revert.setOnClick(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("config//coordpage" + pagenum + ".json"))) {

                // Set coordinates to text fields
                field1.setText(read.readfunct(pagenum.get(),0));
                field2.setText(read.readfunct(pagenum.get(),1));
                field3.setText(read.readfunct(pagenum.get(),2));
                field4.setText(read.readfunct(pagenum.get(),3));
                field5.setText(read.readfunct(pagenum.get(),4));
                field6.setText(read.readfunct(pagenum.get(),5));
                field7.setText(read.readfunct(pagenum.get(),6));
                field8.setText(read.readfunct(pagenum.get(),7));
                pagename.setText(read.readfunct(pagenum.get(),8));

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.add(revert , 1, 12, 5, 1);

        WButton nextpage = new WButton(Text.translatable("→"));
        nextpage.setOnClick(() -> {

            //  /First, Clear all fields
            field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");
            field6.setText("");
            field7.setText("");
            field8.setText("");
            pagename.setText("");
            //  \First, Clear all fields
            pagenum.getAndIncrement();
            pagenumlabel.setText(Text.translatable("  Page: " + pagenum.toString()));
        });
        root.add(nextpage, 2, 1);

        WButton previouspage = new WButton(Text.translatable("←"));
        previouspage.setOnClick(() -> {

            //  /First, Clear all fields
            field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");
            field6.setText("");
            field7.setText("");
            field8.setText("");
            pagename.setText("");
            //  \First, Clear all fields
            if (pagenum.get() == 1 ) {}else {pagenum.getAndDecrement();}
            pagenumlabel.setText(Text.translatable("  Page: " + pagenum.toString()));
        });
        root.add(previouspage, 1, 1);

    }
}


