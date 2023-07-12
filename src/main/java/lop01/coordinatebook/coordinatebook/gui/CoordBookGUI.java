package lop01.coordinatebook.coordinatebook.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import lop01.coordinatebook.coordinatebook.json.read; // lop01.coordinatebook.coordinatebook.encryptdecrypt.encryptdecrypt
import lop01.coordinatebook.coordinatebook.json.write;
import net.minecraft.text.Text;
import lop01.coordinatebook.coordinatebook.playerposition.getposition;
import lop01.coordinatebook.coordinatebook.playerposition.availableslot;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class CoordBookGUI extends LightweightGuiDescription {
    public CoordBookGUI() {
        AtomicInteger pagenum = new AtomicInteger(1);

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(306, 240);

        WLabel label = new WLabel(Text.literal("  CoordKeeper!"));
        root.add(label, 6, 1);

        WTextField pagename = new WTextField();
        pagename.setText("Page Name");
        root.add(pagename, 6, 2, 5, 1);

        // /Row 1
        WTextField field1 = new WTextField();
        root.add(field1, 1, 4, 7, 1);

        WTextField field2 = new WTextField();
        root.add(field2, 1, 6, 7, 1);

        WTextField field3 = new WTextField();
        root.add(field3, 1, 8, 7, 1);

        WTextField field4 = new WTextField();
        root.add(field4, 1, 10, 7, 1);
        // \Row 1

        // /Row 2
        WTextField field5 = new WTextField();
        root.add(field5, 9, 4, 7, 1);

        WTextField field6 = new WTextField();
        root.add(field6, 9, 6, 7, 1);

        WTextField field7 = new WTextField();
        root.add(field7, 9, 8, 7, 1);

        WTextField field8 = new WTextField();
        root.add(field8, 9, 10, 7, 1);
        // \Row 2

        AtomicReference<Boolean> ChecktoogleAES = new AtomicReference<>(Boolean.FALSE);
        WToggleButton toggleAES = new WToggleButton(Text.literal("  Encrypt"));


        toggleAES.setOnToggle(on -> {
            ChecktoogleAES.set((on ? Boolean.TRUE : Boolean.FALSE));
        });

        root.add(toggleAES, 12, 1);

        WTextField EncryptPass = new WTextField();
        root.add(EncryptPass, 12, 2, 4, 1);

        // Buttons!


        WButton save_and_exit = new WButton(Text.translatable("Save"));
        save_and_exit.setOnClick(() -> {
            write writefuncts = new write();
            writefuncts.writeFunctWithGson(ChecktoogleAES, EncryptPass.getText(), pagenum.get(), pagename.getText(), field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText());
        });
        root.add(save_and_exit, 11, 12, 5, 1);

        WLabel pagenumlabel = new WLabel(Text.translatable("    Page: " + pagenum));
        root.add(pagenumlabel, 1, 1);

        WButton revert = new WButton(Text.translatable("Revert To Save"));
        revert.setOnClick(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("config//coordkeeper//coordpage" + pagenum + ".json"))) {
                // Check if the password is correct before setting coords
                if (!(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 0) == "false")) {
                    // Set coordinates to text fields
                    field1.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 0));
                    field2.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 1));
                    field3.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 2));
                    field4.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 3));
                    field5.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 4));
                    field6.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 5));
                    field7.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 6));
                    field8.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 7));
                    pagename.setText(read.readfunct(toggleAES.getToggle(), EncryptPass.getText(), pagenum.get(), 8));
                }else {
                    EncryptPass.setText("Wrong Pass");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.add(revert, 1, 12, 5, 1);

        WButton playerpos = new WButton(Text.translatable("GPS"));
        playerpos.setOnClick(() -> {
            var position = getposition.getcoordinates();
            var slotid = availableslot.getavailableslot(field5.getText(), field6.getText(), field7.getText(), field8.getText());

            if (Objects.equals(slotid, 1)) {
                field5.setText(position);
            }
            if (Objects.equals(slotid, 2)) {
                field6.setText(position);
            }
            if (Objects.equals(slotid, 3)) {
                field7.setText(position);
            }
            if (Objects.equals(slotid, 4)) {
                field8.setText(position);
            }
        });
        root.add(playerpos, 7, 12, 3, 1);

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
            pagenumlabel.setText(Text.translatable("    Page: " + pagenum));
        });
        root.add(nextpage, 3, 2);

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
            if (!(pagenum.get() == 1)) {pagenum.getAndDecrement();}
            pagenumlabel.setText(Text.translatable("    Page: " + pagenum));
        });
        root.add(previouspage, 2, 2);

    }
}