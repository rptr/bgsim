package com.rptr.bradgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AccordionGroup extends HorizontalGroup
{
    ArrayList<Actor> sortedChildren;

    @Override
    protected void childrenChanged ()
    {
        sortedChildren = new ArrayList<>();
        sortedChildren.addAll(Arrays.asList(getChildren().toArray()));
        Collections.sort(sortedChildren, new XComparator());
    }

    public void draw (Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        float x = Gdx.input.getX() - getX();
        float w = getWidth();
        float part = w / sortedChildren.size();

        if (x < 0 || x > w) return;

        for (Actor c : sortedChildren)
        {

            if (x > c.getX() && x < c.getX() + part)
            {
                c.toFront();
                break;
            }
        }
    }
}

class XComparator implements Comparator< Actor > {
    @Override
    public int compare(Actor arg0, Actor arg1) {
        if (arg0.getX() < arg1.getX()) {
            return -1;
        } else if (arg0.getX() == arg1.getX()) {
            return 0;
        } else {
            return 1;
        }
    }
}