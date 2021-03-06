import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import wordcloud.bg.CircleBackground;
import wordcloud.bg.PixelBoundryBackground;
import wordcloud.bg.RectangleBackground;
import wordcloud.font.CloudFont;
import wordcloud.font.FontWeight;
import wordcloud.font.scale.LinearFontScalar;
import wordcloud.font.scale.SqrtFontScalar;
import wordcloud.image.AngleGenerator;
import wordcloud.nlp.FrequencyAnalyzer;
import wordcloud.nlp.tokenizer.ChineseWordTokenizer;
import wordcloud.palette.ColorPalette;
import wordcloud.*

import java.awt.color.*
import java.awt.*
import java.io.*;
import java.lang.Process.*


final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
frequencyAnalyzer.setWordFrequencesToReturn(800);
frequencyAnalyzer.setMinWordLength(5);

def stopWords=["the", "at", "we"]
//frequencyAnalyzer.setStopWords(loadStopWords());
frequencyAnalyzer.setStopWords(stopWords)

//def wordFrequencies = frequencyAnalyzer.load(new FileInputStream ("datarank.txt"));
def wordFrequencies =frequencyAnalyzer.load(new URL("https://en.wikipedia.org/wiki/Whale") )
//frequencyAnalyzer.load(new FileInputStream ("datarank.txt"));
final WordCloud wordCloud = new WordCloud(990, 618, CollisionMode.PIXEL_PERFECT);
wordCloud.setPadding(1);

wordCloud.setBackground(new PixelBoundryBackground(new BufferedInputStream (new FileInputStream("whale2.png"))))
//wordCloud.setBackground(new PixelBoundryBackground(java.lang.Process.getInputStream("whale.png")))
//wordCloud.setBackground(new CircleBackground(300));
//wordCloud.setBackgroundColor(Color.WHITE);
//wordCloud.setBackground(new PixelBoundryBackground(getInputStream("backgrounds/whale_small.png")));

//wordCloud.setBackground(new PixelBoundryBackground(getInputStream("backgrounds/whale.png")));
wordCloud.setCloudFont(new CloudFont("Impact", FontWeight.PLAIN));
//wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0x000000)));
wordCloud.setFontScalar(new SqrtFontScalar(10, 50));
wordCloud.build(wordFrequencies);
wordCloud.writeToFile("whaleOut3.png");
