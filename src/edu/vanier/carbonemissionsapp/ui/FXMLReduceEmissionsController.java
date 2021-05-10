/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author cstuser
 */
public class FXMLReduceEmissionsController implements Initializable {
    @FXML private Button btnWhy;
    @FXML private Button btnReduceCar;
    @FXML private Button btnReduceFootprint;
    @FXML private TextArea txtText;
    
    @FXML
    public void handleBtnWhy(ActionEvent event) throws IOException {
        txtText.setFont(new Font("Times new roman", 15));
        String title = String.format("%100s", "Why should we reduce our carbon footprint?\n");
       
        String environmnent = "Carbon Footprint and the Environment:\n\n"+
                "Our increasing carbon footprint is having profound effects on the environment. Rising temperatures and shifting precipitation\n "
                + "patterns are changing the growing patterns of plants and result in indigenous vegetation moving to increasingly cooler climates.\n" 
                + "Sea levels are rising as the temperature of our planet increases--warmer water occupies more space than cooler water. Rising seas\n" 
                + "will not only erode shorelines and destroy ecosystems, coastal cities and towns could be displaced by rising seas.\n";
        
        String wildlife = "Carbon Footprint and Wildlife:\n\n" +
                "As vegetation shifts climates because of increasing temperatures and shifting weather patterns, wildlife that depends on it will\n"
                + " become threatened because it is unable to keep up with the rate at which the climate is changing. For example, migratory birds\n"
                + " arrive at their destination to find that food sources such as plants bloomed too early or not at all and melting Arctic ice destroys\n"
                + " hunting ground for polar bears. According to the Nature Conservancy, one quarter of the Earth's species will be headed for\n"
                + " extinction in 40 years if climate change increases at its current rate.\n";
        
        String health = "Carbon Footprint and Human Health:\n\n" +
                "Our increased carbon footprint has the capacity to harm our health. Most at risk are women in agricultural work and children.\n"
                + " According to the World Health Organization, climate change is projected to increase the percentage of people in Mali suffering\n"
                + " from hunger from 34 percent to at least 64 percent 40 years from now. An increase in malnutrition is caused by the result of\n"
                + " climate change on food crops, such as drought that interferes with the growing season. Drought also causes diarrheal diseases\n"
                + " as access to safe water is compromised. Vector-borne diseases such as malaria are increasing as the temperature increase allows\n"
                + " malarious mosquitoes to survive in countries previously too cool for them. Lastly, increased air pollution has caused an increase\n"
                + " in respiratory problems as asthma and allergies have increased.\n";
        
        String economics = "Carbon Footprint and Economic Losses:\n\n" +
                "The threat posed by our increasing carbon footprint on the economy is significant. Climate change will affect local economies\n"
                + " dependent on land and natural resources the most, such as farms that fall victim to lowered crop yields. For example, according\n"
                + " to the Nature Conservancy, economic losses due to our increasing carbon footprint and the resulting climate change has\n"
                + " threatened the lobster industry in New England as catches have plummeted. In addition, the increase in ocean temperatures is\n"
                + " threatening the survival of coral reefs, a $375 billion per year industry.\n";
        
        String provider = "Ressources: https://sciencing.com/overpopulation-deforestation-amazon-basin-23665.html";
        
        txtText.setText(title + "\n" + environmnent + "\n" + wildlife + "\n" + health
                + "\n" + economics + "\n" + provider);
    }

    
    @FXML
    public void handleBtnReduceCar(ActionEvent event) throws IOException {
        txtText.setFont(new Font("Times new roman", 15));
        String title = String.format("%120s", "How to Reduce the carbon emission of your car.\n");
        
        String alternatives = "Alternatives to Driving:\n\n"
                + "When possible, walk or ride your bike in order to avoid carbon emissions completely. carpooling and public transportation\n"
                + "drastically reduce co2 emissions by spreading them out over many riders.\n";
        
        String fuel = "Drive a fuel efficient vehicle:\n\n"
                + "All vehicles have an estimated miles-per-gallon rating. Electric cars still have carbon emissions because they’re usually charged\n"
                + "with electricity created by the burning of fossil fuels; however, their MPG equivalents (MPGe’s) are typically much higher than\n"
                + "conventional and hybrid cars. In some situations, electric cars like the Nissan Leaf are essentially free.\n";
        
        String cargo = "Get a hitch-mounted cargo rack:\n\n"
                + "Don’t buy a minivan or SUV if you don’t need 4WD and/or will only occasionally need the extra space. A receiver hitch and\n"
                + "a rack like this one only cost a few hundred bucks. Avoid roof-top boxes, which cost much more, increase aerodynamic drag,\n"
                + "and decrease fuel economy.\n";
        
        String style = "Driving style:\n\n"
               + "Speeding and unnecessary acceleration reduce mileage by up to 33%, waste gas and money, and increase your carbon emissions.\n";
       
        String inflation = "Tire inflation and other tuning:\n\n"
                + "Properly inflated tires improve your gas mileage by up to 3%. It also helps to use the correct grade of motor oil, and to keep\n"
                + "your engine tuned, because some maintenance fixes, like fixing faulty oxygen sensors, can increase fuel efficiency by up to 40%.\n";
        
        String misc = "Misc:\n\n"
                + "Combine errands to make fewer trips. Remove excess weight from your car. Use cruise control.\n";
        
        String provider = "Ressources: https://cotap.org/reduce-carbon-emissions/";
       
        txtText.setText(title + "\n" + alternatives + "\n" + fuel + "\n" + cargo 
                + "\n" + style + "\n" + inflation + "\n" + misc + "\n" + provider);
    }
    
    
    @FXML
    public void handleBtnReduceFootprint(ActionEvent event) throws IOException {
        txtText.setFont(new Font("Times new roman", 15));
        String title = String.format("%120s", "How to Reduce your carbon footprint.\n");
        
        String insulation = "Insulate and seal your home:\n\n"
                + "Reduce drafts and air leaks with caulk, insulation, and weather stripping. Many states offer programs and incentives to facilitate\n"
                + "this, and a great example is Energy Upgrade California.\n";
        
        String appliances = "Appliances:\n\n"
                + "Make energy efficiency a primary consideration when choosing new appliances like furnaces, air conditioning units, dishwashers,\n"
                + "and refrigerators. ENERGY STAR labeled products are recognized as having superior energy efficiency.\n";
        
        String lighting = "Lighting:\n\n"
                + "Turn off lights you’re not using and when you leave the room. Replace incandescent light bulbs with compact flourescent or LED\n"
                + "ones.\n";
        
        String thermostat = "Thermostat:\n\n"
                + "Don’t set it too high or low. Install a programmable model to turn off the heat/air conditioning when you’re not home.\n";
        
        String solar = "Solar:\n\n"
                + "Add solar panels to the roof of your home. This costs a little more than the above options, but many providers offer financing\n"
                + "options which minimize upfront costs. Two examples are SolarCity and SunRun. If you live in a state with a Net Metering law,\n"
                + "you could eliminate your electricity bill or even earn money by selling electricity back to the grid.\n";
        
        String water = "Water usage:\n\n"
                + "Lower the amount of energy used to pump, treat, and heat water by washing your car less often, using climate-appropriate plants\n"
                + "in your garden, installing drip irrigation so that plants receive only what they need, and making water-efficient choices when\n"
                + "purchasing shower heads, faucet heads, toilets, dishwashers and washing machines.\n";
        
        String recycle = "Reuse and recycle:\n\n"
                + "It has been estimated that 29% of U.S. greenhouse gas emissions result from the “provision of goods,” which means the\n"
                + "extraction of resources, manufacturing, transport, and final disposal of “goods” which include consumer products and\n"
                + "packaging, building components, and passenger vehicles, but excluding food. By buying used products and reselling or\n"
                + "recyling items you no longer use, you dramatically reduce your carbon emissions from the “provision of goods.”\n";
        
        String food = "Food:\n\n"
                + "It has been estimated that 13% of U.S. greenhouse gas emissions result from the production and transport of food. Buy local\n"
                + "and eat a more diversified diet including less meat and dairy to reduce your carbon emissions resulting from the use of\n"
                + "fossil fuel-based fertilizers, pesticides, and gas required to produce and transport of the food you eat.\n";
        
        String energy = "Support clean energy sources:\n\n"
                + "Whenever you can, advocate for clean alternatives to fossil fuels, such as wind, solar, geothermal, and appropriately designed\n"
                + "hydroelectric and biomass energy projects.\n";
        
        String phone = "Unplug Your Devices:\n\n"
                + "You might be surprised to learn that all electronics suck energy when they’re plugged in, EVEN IF they’re powered down.\n"
                + "In the U.S. alone, “vampire power” is responsible for draining up to $19 billion in energy every year. Anytime a cord\n"
                + "is plugged into a socket, it’s drawing energy – so although your device isn’t charging, you’re still contributing to\n"
                + "your carbon footprint. Simple solution? Leave your electronics unplugged at all times, unless you’re actually\n"
                + " using them.\n";
        
        String clothes = "Line-Dry Your Clothes:\n\n" 
                + "New is not always better – the traditional method of line-drying your clothing is much better for the environment. One dryer load uses 5 times more electricity\n"
                + "than washing – by simply line-drying your clothes, you can save 1/3 of their carbon footprint. Unfortunately, line-drying seems to be America’s least favorite\n"
                + "way to save energy – despite the fact that running a clothes dryer is equivalent to turning on 225 light bulbs for an hour. Europeans, on the other hand, are\n"
                + "excellent at this (95 percent of Italians don’t even own a dryer) – it might be time to follow their lead, especially considering the tumble dryer is one of the\n"
                + "top energy-consuming appliances (not to mention the leading cause of appliance-related house fires) Anyone can make these simple changes: they’re easy to\n"
                + "implement, and are immediately effective in reducing your carbon footprint. Our actions and choices ultimately make a difference, and we all share the\n"
                + "responsibility to do whatever we can to address climate change, big or small.";
        
        String provider = "Ressources: https://www.huffpost.com/entry/7-instant-ways-to-reduce-your-carbon-footprint_b_59321992e4b00573ab57a383 and\n"
                + "https://cotap.org/reduce-carbon-emissions/";
        
        txtText.setText(title + "\n" + insulation + "\n" + appliances + "\n" + lighting 
                + "\n" + thermostat + "\n" + solar + "\n" + water + "\n" + recycle 
                + "\n" + food + "\n" + energy + "\n" + phone + "\n" + clothes + "\n" + provider);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
