/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.ericdraken.interviews;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest
{
	private static String[][] nthFibs()
	{
		// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
		// e.g. If the user enters 1 as the number of desired Fib sequence numbers,
		// then the first number in the sequence is 0, so 0 should be returned.
		// Source: https://www.bigprimes.net/archive/fibonacci/10000/
		return new String[][]{
			{"1", "0"},
			{"2", "1"},
			{"3", "1"},
			{"4", "2"},
			{"10", "34"},
			{"51", "12586269025"},
			{"101", "354224848179261915075"},
			{"201", "280571172992510140037611932413038677189525"},
			{"501", "139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125"},
			{"1001", "43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"},
			{"10001", "33644764876431783266621612005107543310302148460680063906564769974680081442166662368155595513633734025582065332680836159373734790483865268263040892463056431887354544369559827491606602099884183933864652731300088830269235673613135117579297437854413752130520504347701602264758318906527890855154366159582987279682987510631200575428783453215515103870818298969791613127856265033195487140214287532698187962046936097879900350962302291026368131493195275630227837628441540360584402572114334961180023091208287046088923962328835461505776583271252546093591128203925285393434620904245248929403901706233888991085841065183173360437470737908552631764325733993712871937587746897479926305837065742830161637408969178426378624212835258112820516370298089332099905707920064367426202389783111470054074998459250360633560933883831923386783056136435351892133279732908133732642652633989763922723407882928177953580570993691049175470808931841056146322338217465637321248226383092103297701648054726243842374862411453093812206564914032751086643394517512161526545361333111314042436854805106765843493523836959653428071768775328348234345557366719731392746273629108210679280784718035329131176778924659089938635459327894523777674406192240337638674004021330343297496902028328145933418826817683893072003634795623117103101291953169794607632737589253530772552375943788434504067715555779056450443016640119462580972216729758615026968443146952034614932291105970676243268515992834709891284706740862008587135016260312071903172086094081298321581077282076353186624611278245537208532365305775956430072517744315051539600905168603220349163222640885248852433158051534849622434848299380905070483482449327453732624567755879089187190803662058009594743150052402532709746995318770724376825907419939632265984147498193609285223945039707165443156421328157688908058783183404917434556270520223564846495196112460268313970975069382648706613264507665074611512677522748621598642530711298441182622661057163515069260029861704945425047491378115154139941550671256271197133252763631939606902895650288268608362241082050562430701794976171121233066073310059947366875"},
			{"70333", "14764850684189816403580140143363762977190377750284997600081715975272095626237007191933058991712470461789480075339733520643077652900244040642777957199846614952653615549551842821757379649841482454956957335347567317796005172391978162470788509886647353391570150596787247471740604960252169816185679374267324363719369233047906037013933297132149009750631813193927617024277421180936426016744723335128085616188613051298073247681435487069610662088538747806784473095917967888143065159222434747519205444439749997347585066903905757234759688528877449278938692787193544610028515312427043698104371225335376967927550948309172427481928633845854641321462601454498236202194352087853322929403935521154682279490489952555675151146294679626556757911379367136627054507548995995327177472330777557390401300207548311608397841552986498956429508002571169976376084549032971293224465764063513041025477955348484927500537267908644254950503396733770666202844306292349063403055918912071290902510350446549833365593697962031270622167202995060762482620009793051517283720252302094592676749533490381251640551938752173387075527584350991393006046794162706980641780283882450961612082238390871946996171588122965298524172202150167621051235195857484856088355841573731528271211181720909867034253783679850801955266399496630833439703091329473202315111959780974268909196511202826145057164687924445950769435459152865374401952098550016937404217549276081775433053111926089458415202250135602256491706642955979753434210904101253104788846243264827245466962291087947607228510619086477964224302610931136891794587934430008055162460782286071311845506618042325435188007950675877309141639963476647240202508221527147962460284357018962087498645561799272116385807712602812717481276659453535305350952231987516972372953127325142934502038186199875536504580807832008398589557752479478928997380283906561566369975136813952485227291668341427894350969112457746502437290133425333183413246077175440180381126744152782080138876855312061837898009892620319553244308676245120740608300455287970421524564402101550643173525591114344474690140556830604660909036310851325288155418548485123458753459076092291800765631361087417124524639477107474869307579272706511650672224992516630032464658421321016816931682920867967799431858865654409269320174761827086828910001638550254699870676487439826733127057879828455605824665597027120864047746161968684304076983018593831595938427128375598745729523730175710046764683220768551605159036638673023456843495643155467902184176562057318150254733985804644802560580189982409962717851816901445456250576572317119408929871680151512932621583194963048613195354852703852400755117018173178183640596153895407197140552862745373895298517265245678680151290668272322838441788285285853868307219644639152670052736064671278420321991751142707216832303459113268331719783623385870076454220996787949682825787138837998667809305018073019501623448708247816677151246012564492826807854491871755074059597403139119383566289689958539963382956419666634463640619926687095165027910416728916987376836466543735873548855016899944399127620886029631110495679245127182012655217760523147536296007328528544631918165158685449250910957414057683187579644400945847240467861867929695404224701336632218994069626148103299237009365889143459040634265277918366133361138858282579147797772840119498222030695386195665459197223050509455759696295826342659143209455067143574863401456908487833596963668088964913124092536917506224220429967669152601805626147244317463315005098605229086531126116042346412554310218309664450044217103177809717760226715323718779993216687391518708450471026122589602217223387215722705535322540747353688834733003615437473418964649805414525926255091414489443716577102234449733193799537505066797188353420651826496173932868134741489410674294671879310032146420949040717674607736889521203044919816621905558276297907751435839605882505651236445559501987322501230579081365662098711006371342772819820009186930578344733698466524225271255471474248383396353436771863377119232131433947279783985960142814978145133597301941015118262583968939092989235416083570638594225715864839398509524819192976283545626231287255904075281471604018301434401225586643610803763161596852073604190632919339279226843875309432959278486454628806006194077917938002550284371761591927707937935889331899729232032847407886150396074270282639786852608469532100258939760608991149873348069138983259005480153971789355379126585536949510294227945154208476369273849109359121518424900026030349479261809126864938388858742498082135934796083121144531852625147715736575444537242136210000047041315550244062871412930570732198487301070538694116955576968577492739014984467497582020427888590251735879747246995606495993803176797516783532520828190247607695508260047227182931602168576021361222186124931338027649439948936146769129026527059614298243489962823118851520562832219016659006982696905633823613281337997144019262979492720514374542705834489595554908636094238917393745974297758059065608661274549107028039966503342809285093089633526291496996286581335430229785701019954582696485221831697515171677057316665097977091546315737112113453251836036180379786513154747472434227614168931581810457155989225867739517849696564444334335863458360289681497230187628867633220450565122107651669532488823645181854509941400612192418356997112530405181527800567178762024908290007875852999612380094150965307129269485958670176169222513018644294274977689386716140304750433999880303930143245830223168081109066507591835696543261030728260512506860203181110517315987533148664219431158811863687113413415466399985214489751515012175371755346996139500799915324900289598206124234111367218363558466880913510844634091201685229891078668081363951052006557129948361407678941833784333625316414344717805873551833347587534478122875857551199390236559744336407102063405624900230276603126458126725818441367119936352634247151994088979602267376654486619232127124949998381826693703178754224037256162644400976972234815830853131917364629419091137372283506078247024062475794646399022902348071196207866960152846833489817684965514678503579410830923003175898715551857675381226726024830146606739025204857661598213721984619203133493674022502412927719927211848471082263060143017479184491633086868383573254827797086849557986429683797824012269880060528652863600924696691977841321083192032105397843002113530394660511331235135370318431124434265225741907339983620106175036626706666425183940601936066600324165879951772769061665375925234968137020832401889247765977822563115483094582016820888104617048338148856677160826859313680152671118364827693451531693114190157368342731402230967654771845736578370193624123356206718729913689998264541605551100351529111596676613355164643928284724443373612376105148351915691650489553208491077005366155521409313154806052558302460765118866984740857594716747940898474358339404612389427326310322110938332590821532416024247257293579454513821181776529984768544854681982178846390419335162902407866931550759816717475136922433277275235312455419471680905990563218593821033812037495708286901320629916978121942597531975698088115758732615154099837702030672262589027362422877241212014989511942314971403095949376676845147864764743205952372105352133033569849297456649846366775094383319755142935027678011150479724690244286756902024129508069106337974410965919843355132888060568465419120984036095703608896360910649682802449698438615853501593472486864215766056452636798591499935939113759465440190361410822960746812934009724977366287005215523358817609209574091546966008864152755426361804210459398990864657895419637659893567574829341571438336270383620726656733593070442418918179446762925500078404283209356794527660943710073634437930787683037546818800092324540816575071249117801991168802106586297884629770231928632325480691967851962218950612406940273957211673703626860464829461433545303446889953421126972539851691317669760350295043554577103616582483007634200731549621952941234285988954861642434651714370258320212161052433051540785906877931080900926151192975839173027376992151877191243962460135716584223100263637399353667575715725876945618114713415173482588350861424900143053639931158346847907226639641736404392381369791693292949216821604952757885109138184443464117556886249732350359794291373078349716996357913561825817656068839714266157931074422821736476347800106458141517478656444852025488695365381076744340066056691232818903916129235849541922994708595932903850068157677381752375960303740526378496979041130377756191089018897056563236343719315887918065386052994474979842527575144042839942620230538403420357526643379376938418476551651108442475367764857979257989648691380046940023375389641108472723722208633505707323132088250625482503829855071180627663513694011767617927660577721330870126177693332525050892545296976384426468742786427902475729974224420648597812241328636652643792435418115031122679578924962367489400418733789306279211444562124047578490971251466983605157568659374163931454034413655899597733375321144370852532190463544038703145934474443165477259615008721846463243381596571814648662862900675702170236410737149563266339714879305304135896863731953225919262579708913223257401734295473544116597569587717205100526796041783498987124595841122393666932553382956797286780804239436782105311767307520610079520504137417525638943835962421664949279887083280045570585906897513506109415974748536331396538177342478126325898748232336559858374723376431079107724333192892065574663553485103921062414179571134904145357298802586983262507742635020659871652824284256718665024126175892210035014617675341563844277813544767747435298301134637199179932033725534138900958773775338752051578968100863264681072898843888955844975159687529248171741694002873691410692681209285680126058784154844821683193310160145697392199045598446359851397245381336738707055918566959989706154947619480453081462457852069929422361719188382244480009850690766813115261508922175510431485242264050322245085869745697989779744219921549397495312920830848173846519068165250965122579339581785812107355676983747969846375247012396132608436840505995287315342113615033978810551640236158788676365250522083376554359802124063727685523989913049286290896121989921127654184915770733357805725525779956427974110849324062202484551256203622051222480364097865012843778032763859223989916689793079109952427086217700965853851989053307523138288712266194149445549640293904062033458524846241707117749192828099472450777966805548325898649024959986047390988387793013409554510051847028725046471813673569251602016295738153341072569890544828910008477651396649255606269120796339762553303327218862694188993963333586510593799110233537879919292846297704664076927142764198236391627566102265681252881940576794207902291706217990071934424079974363977919689016400029156971328139727089053816180015396778050662056569047966032387398184787606455424588241163124613132581720538760960974730727895577141308192731178275885020278464645113221899140602210271623360853029149742397670766320194688291748343863346042659502837318532765675224553087036591626452117535758720366019021144989350405745271382846887782689899187524981003118564169335134201150334434346437595474209458919749829375020094562881319484750473188647664690321717813421022746655123208272744176368479921185863703320890985533180575534118271177098326124205517769169844107275669306613392201644600965054856481582052526567469201678548553097877072849593106405873261758185980995651833445492681036335154405135473576075291748569669729360865614987457490250361637903728634366983788150497684125691202902167093698592994735182759069373007452948419237928855631125540030481345287901133664054048019045270178460166884971638250096117463012362689109130820380861378492471041379482103106344935928171549062494607809839939789387037552913109598218398157156686930097895317242209940607336723197937061327205980041571594835170481271159947320592305926611690306544606812707161061081103843018769783464534543528710259247853849897760046419871527755980759848400902657617067814170116770597890768638936785524299996075482718940394790650134183711457518062980158132693929510988796968746136266885970292784707162991094629842621375674186461430309662173669118435785095635281085016934268159646656729062849214498904484607494987407714072040185810453065703475362496693828755749553069046989537888435089831769302852138987661631527467591922093577568808375502386289891711978987353737593812602448400362069261288430796666952363092295192805582169477966825404213788137803121344878719299091572020806086892974024437020675558476200951698178548033967433449743399306408155577238625334666450640609355670656836509015841478745513999408306319708227832253870546460259343545957248179956989836402439950847496363371273250422619995170444018219513203713596648726181022355249917762734891578759611103566028204155945198106605899676782979452601430613060055463353492628431543741475244332524802728215126786834039076524950392289887193642079440124746357259023459665721353946867155473425424184546741247305936597558483538331606796510498032093246867109236737152363665651245750800096383550006893512110985609431377128711745319007706826646226757535609138123157241883518531983143293910107405496345306666665344596214127181327004747878964071670995679944193360749150293239414593622023462741377685512771462906920099338320102179354190588373282733789617546841761802737810434577607490154538212306204958049142808702722999691532818851932800719698693947003110568574264357636813645111978053321336341734045057220029820277948295992696770797584561274024624875397940238560109280745333157726775489536000793715030994682815370423750863572545461470136954498973858095332065165518503469414044226038854777802682476294065571371581504166485414728782397769494349686284403598882420671202189280677839368072395251567441354773387311047129934084117842866309805370351117367035984282271777367949463281387835167830876082921942405877214596069172651098283277061629779729543709337615165755727486631830880723207629430830469173052901667662531157170824483708056680526734483987798326801848744821333715435470451544191157353292014591639803324942129354454201510888890661771024657281050490313539354521567052942402502971999497057869292140638285812697201296552233999491403356473178250292744241045474775907263643220271859228109952374433172066542294587066339777153194796743346927507562968699293424290796964749362033812746074895281104942047726434949471299884856334502745443515848514757589995962180450932614011711532333322500172229961195193933746956292601710620002611732995411074357321074045718139740686078329100541026897537518710464363710078692780042905286203509340659000464543091704727870441134964319142596740849438493561583648950275570256220785470366637214468980525302337266086305831753292933255224417238618495297193881934168386727199473512624028740561108701615370379885146303000784"}
		};
	}

	@ParameterizedTest
	@MethodSource( value = "nthFibs" )
	void getSequenceAsArray_valid( String num, String nthFib )
	{
		int numFibs = Integer.valueOf( num, 10 );
		BigInteger[] fibs = Fibonacci.getSequenceAsArray( Integer.valueOf( num, 10 ) );

		// Length of the sequence must match the supplied length
		assertEquals( numFibs, fibs.length );

		// The last number in the Fibonacci sequence must match that expected
		assertEquals( new BigInteger( nthFib ), fibs[ numFibs-1 ] );
	}
}