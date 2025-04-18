package com.core.screens.subscreen.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.customcomponent.button.LevelBtn;
import com.core.data.game.container.CardData;
import com.core.data.game.container.CategoryData;
import com.core.data.game.container.LevelData;
import com.core.screens.base.BaseSubScreen;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.util.transition.SlideOutScreen;
import com.main.Main;

public class LevelSelectScreen extends BaseSubScreen {

    private final Color levelIconColor;
    private final Table levelButtons;
    private final Table rootTable;
    private final int levelCount;
    private final ScrollPane scrollPane;
    private final TextureRegionDrawable btnIcon;
    private float btnPaddingH;
    private float btnPaddingV;
    private float btnSize;
    private final String levelCategoryName;


    public LevelSelectScreen(Main main, String levelName, int levelCount, Color levelIconColor) {
        super(main);
        this.levelIconColor = levelIconColor;
        levelIconColor.add(-0.01f, -0.01f, -0.01f, 1);
        this.levelCount = levelCount;
        this.levelButtons = new Table();
        this.rootTable = new Table();
        this.rootTable.setDebug(true);
        this.rootTable.top();
        this.levelCategoryName = levelName;
        this.btnIcon = new TextureRegionDrawable(main.getBaseAssets().assetManager.get("levelbtn.png", Texture.class));
        calculateSizes();
        this.scrollPane = new ScrollPane(levelButtons);
        this.scrollPane.setScrollingDisabled(true, false);
        this.rootTable.add(scrollPane);
        loadLevel();
        super.stage.addActor(rootTable);
    }

    private void calculateSizes() {
        this.rootTable.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.8f);
        this.rootTable.setPosition(
            Gdx.graphics.getWidth() / 2 - rootTable.getWidth() / 2,
            Gdx.graphics.getHeight() * 0.1f
        );
        this.btnPaddingH = Gdx.graphics.getWidth() * 0.05f;
        this.btnPaddingV = Gdx.graphics.getWidth() * 0.02f;
        this.btnSize = (this.rootTable.getWidth() / 2.78f) - (btnPaddingV + btnPaddingH);
        btnIcon.setMinSize(btnSize, btnSize);
    }

    public void createLevelButton(int num) {

        float btnNumWidth = 0;
        float btnNumHeight = 0;

        if (num >= 10) {
            btnNumWidth = btnSize * 0.7f;
            btnNumHeight = btnSize;
        } else {
            btnNumWidth = btnNumHeight = btnSize * 0.5f;
        }

        TextureRegionDrawable numberTexture = new TextureRegionDrawable(main.getBaseAssets().getBitNumbers().getNumber(num));

        numberTexture.setMinSize(btnNumWidth, btnNumHeight);

        if (numberTexture == null) return; // TODO: ERROR MSG


        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.imageDown = numberTexture.tint(Color.GRAY);

        style.imageUp = numberTexture.tint(levelIconColor);

        style.up = btnIcon.tint(levelIconColor);

        style.down = btnIcon.tint(Color.GRAY);

        LevelBtn btn = new LevelBtn(style);

        btn.setSize(btnSize, btnSize);

        btn.levelIdx = num - 1;

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LevelBtn btn = (LevelBtn) event.getListenerActor();

                if (((LevelBtn) event.getListenerActor()).isDisabled()) return;
                try {
                    main.screenManager.switchScreens(createLevel(btn.levelIdx), SlideOutScreen.SlideDirection.LEFT);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(e);
                    Gdx.app.exit();
                }


            }
        });

        levelButtons.add(btn).pad(
            btnPaddingV / 2, btnPaddingH / 2,
            btnPaddingV / 2, btnPaddingH / 2
        );
    }

    public void loadLevel() {
        for (int i = 1; i <= levelCount; i++) {
            createLevelButton(i);
            if (i % 3 == 0) levelButtons.row();
        }
    }

    private LevelScreen createLevel(int idx) throws IndexOutOfBoundsException {

        CategoryData category = main.getGameConfig().getCategories().get(levelCategoryName);
        LevelData levelData = category.getLevels()[idx];

        if (levelData == null) {
            throw new IndexOutOfBoundsException(String.format("level index of (%d) is not found", idx));
        }

        Level level = new Level(main);

        for (CardData card : levelData.getCards())
            level.addPair(card.getWord(), card.getImg(), card.getAudio());

        return new LevelScreen(main, level);
    }
}
