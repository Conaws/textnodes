<?xml version="1.0" encoding="utf-8"?>
<!-- Created by Leo: http://leoeditor.com/leo_toc.html -->
<leo_file xmlns:leo="http://leoeditor.com/namespaces/leo-python-editor/1.1" >
<leo_header file_format="2" tnodes="0" max_tnode_index="0" clone_windows="0"/>
<globals body_outline_ratio="0.5" body_secondary_ratio="0.5">
	<global_window_position top="50" left="50" height="500" width="700"/>
	<global_log_window_position top="0" left="0" height="0" width="0"/>
</globals>
<preferences/>
<find_panel_settings/>
<vnodes>
<v t="conor.20160605011037.1"><vh>@path ~/orgmap/leo/text-nodes</vh>
<v t="conor.20160605011510.1"><vh>@auto log.org</vh></v>
<v t="conor.20160605013539.1"><vh>@auto specter.org</vh></v>
</v>
<v t="conor.20160605013539.1"></v>
<v t="conor.20160605013848.1"><vh>@auto datascript.org</vh></v>
<v t="conor.20160605010926.1" a="E"><vh>@path src/</vh>
<v t="conor.20160605011023.2" a="EM"><vh>/clj/</vh>
<v t="conor.20160605011025.1" a="EM"><vh>/text_nodes/</vh>
<v t="conor.20160606052632.1" a="E"><vh>@auto spec-test.clj</vh></v>
<v t="conor.20160605012522.1" a="M"><vh>@auto core.clj</vh></v>
<v t="conor.20160605011026.1" a="M"><vh>css.clj</vh></v>
</v>
</v>
<v t="conor.20160605011023.1" a="M"><vh>/cljs/</vh>
<v t="conor.20160605011952.1" a="EM"><vh>/text_nodes/</vh>
<v t="conor.20160605011953.6" a="M"><vh>config.cljs</vh></v>
<v t="conor.20160605011953.5" a="M"><vh>@auto core.cljs</vh></v>
<v t="conor.20160605011953.4" a="M"><vh>@auto db.cljs</vh></v>
<v t="conor.20160605011953.3" a="M"><vh>@auto handlers.cljs</vh></v>
<v t="conor.20160605011953.2" a="M"><vh>@auto subs.cljs</vh></v>
<v t="conor.20160605011953.1" a="M"><vh>@auto views.cljs</vh></v>
</v>
</v>
</v>
<v t="conor.20160606053224.1"><vh>spec-test</vh>
<v t="conor.20160605013418.1"><vh>namespace -- datascript and specter</vh></v>
</v>
</vnodes>
<tnodes>
<t tx="conor.20160605010926.1"></t>
<t tx="conor.20160605011023.1">@path cljs</t>
<t tx="conor.20160605011023.2">@path clj</t>
<t tx="conor.20160605011025.1">@path text_nodes</t>
<t tx="conor.20160605011026.1"></t>
<t tx="conor.20160605011037.1"></t>
<t tx="conor.20160605011952.1">@path text_nodes</t>
<t tx="conor.20160605011953.6"></t>
<t tx="conor.20160605013418.1">(ns text-nodes.core
  (:require 
            [com.rpl.specter :as sp :refer [ALL]]
            [clojure.spec :as s] 
            [clojure.pprint :refer [pprint]]
            [datascript.core  :as d])
  (:use [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))</t>
<t tx="conor.20160606053224.1"></t>
</tnodes>
</leo_file>
