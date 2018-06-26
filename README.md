[TOC]

### 首页获取用户指数
```
GET /home/health_index
```
res

```
{
    "code": 200,
    "data": {
        "score": 23,
        "health_information_id": 5,
        "health_monitoring_id": 5,
        "health_information_complete": true,
        "have_health_assess": true
    },
    "message": "success"
}
```
- ```score``` 分数
- ```health_information_id``` 查询或更新健康档案使用
- ```health_monitoring_id ``` 查询或更新健康档案使用
- ```health_information_complete ``` 健康档案是否填写完成
- ```have_health_assess ``` 是否有健康评估报告

###个人疾病史添加
```
GET /self_disease/information/{health_information_id}
```
res

```
{
    "code": 200,
    "data": {
        "disease_history": [0,1,2,5,6],
        "drug_history": [0,2,3,5]
    },
    "message": "success"
}
```

- ```disease_history ``` 疾病史 0 表示：1型糖尿病;	1 表示：2型糖尿病;	2 表示：高血压;	3 表示：血脂异常;	4 表示：痛风/高尿酸血症;	5 表示：哮喘;	6 表示：冠心病;	7 表示：类风湿性关节炎;	8 表示：慢性便秘;	9 表示：胃/十二指肠溃疡病;	10 表示：骨质疏松症;	11 表示：骨折;	12 表示：脑卒中/脑中风;	13 表示：慢性胆囊炎/胆石症;	14 表示：肺结核;	15 表示：甲亢;	16 表示：甲减;	17 表示：甲状腺结节;	18 表示：甲状腺癌;	19 表示：食管癌;	20 表示：肺癌;	21 表示：前列腺癌;	22 表示：慢性肾炎;	23 表示：脑出血;	24 表示：代谢综合征;	25 表示：胃、肠息肉;	26 表示：肝硬化;	27 表示：慢性腹泻;	28 表示：乙肝;	29 表示：脂肪肝;	30 表示：结直肠癌;	31 表示：丙肝;	32 表示：肝癌;	33 表示：胃癌;	34 表示：慢性支气管炎;	35 表示：前列腺疾病(前列腺增生/肥大/前列腺炎);	36 表示：慢性肾病(肾炎/肾病综合症/慢性肾功能不全); 37 表示肺气肿； 38 表示心肌梗死	
- ```drug_history``` 药物史 0 表示：降压药;	1 表示：降糖药;	2 表示：调脂药(降脂药）;	3 表示：降尿酸药;	4 表示：抗心律失常药;	5 表示：缓解哮喘药物;	6 表示：镇静剂或安眠药;	7 表示：中草药;	8 表示：激素类药物;	9 表示：解热镇痛药;	10 表示：精神类药物;	11 表示：其它;	12 表示：抗血小板类药物(如阿司匹林等);	


###个人疾病史添加或更新
```
POST /self_disease/information/{health_information_id}
```
req

```
{
	"disease_history":[0,1,2,5,6],
	"drug_history":[0,2,3,5]
}
```


###查询家族疾病史
```
GET /family_disease/information/{health_information_id}
```
res

```
{
    "code": 200,
    "data": [
        {
            "disease": [
                19
            ],
            "type": 5
        },
        {
            "disease": [
                13
            ],
            "type": 2
        },
        {
            "disease": [
                21
            ],
            "type": 1
        }
    ],
    "message": "success"
}
```

- ```disease``` 疾病类型 0 表示：糖尿病;	1 表示：高血压;	2 表示：高脂血症;	3 表示：哮喘;	4 表示：甲状腺癌;	5 表示：肺癌;	6 表示：肝癌;	7 表示：食道癌;	8 表示：宫颈癌;	9 表示：卵巢癌;	10 表示：慢性支气管炎;	11 表示：痛风(高尿酸血症);	12 表示：脑卒中(脑中风);	13 表示：冠心病;	14 表示：结直肠癌;	15 表示：前列腺癌;	16 表示：大肠癌;	17 表示：鼻咽癌;	18 表示：乳腺癌;	19 表示：子宫内膜癌;	20 表示：髋部骨折; 21 表示 心血管疾病；22 表示高胆固醇血症; 23 表示肺气肿;
	24 表示心肌梗死
- ```type``` 0 表示：父亲;	1 表示：母亲;	2 表示：兄弟;	3 表示：姐妹;	4 表示：(外)祖父母;	5 表示：(外)孙子女;	6 表示：叔舅姑姨;	7 表示：外甥(女)侄子(女);	

###家族疾病史添加或更新
```
POST /family_disease/information/{health_information_id}
```
req

```
[
    {
        "disease": [13],
        "type": 2
    },
    {
        "disease": [21],
        "type": 1
    },
    {
        "disease": [19],
        "type": 5
    }
]
```


###查询吸烟情况
```
GET /smoking/monitoring/{health_ monitoring_id}
```
res

```
{
    "code": 200,
    "data": {
        "smoking_type": 1,
        "daily_smoking": 12,
        "start_smoking_age": 10,
        "have_some_one_smoking": true,
        "smoking_than15": 7
    },
    "message": "success"
}
```

- ```smoking_type ``` 0 表示：不吸烟；	1 表示：吸烟； 2 表示：戒烟
- ```daily_smoking``` 每日吸烟量 // 选择吸烟是必传改字段 默认0
- ```start_smoking_age``` 开始吸烟年龄 //选择吸烟时必传 默认0
- ```have_some_one_smoking``` 周围是否有人吸烟 // 选择不吸烟或者戒烟时必传 默认 否
- ```smoking_than15``` 吸烟超过15分钟 // 选择吸烟和戒烟时必传 默认0
- ```start_give_up_smoking_age``` 开始戒烟年龄 // 选择戒烟时 必传 默认0

###添加或更新吸烟情况
```
POST /smoking/monitoring/{health_monitoring_id}
```
req

```
{
    "smoking_type": 1,
    "daily_smoking": 12,
    "start_smoking_age": 10,
    "smoking_than15": 40
}
```

- ```smoking_type ``` 0 表示：不吸烟；	1 表示：吸烟； 2 表示：戒烟
- ```daily_smoking``` 每日吸烟量 // 选择吸烟是必传改字段 默认0
- ```start_smoking_age``` 开始吸烟年龄 //选择吸烟时必传 默认0
- ```have_some_one_smoking``` 周围是否有人吸烟 // 选择不吸烟或者戒烟时必传 默认 否
- ```smoking_than15``` 吸烟超过15分钟 // 选择吸烟和戒烟时必传 默认0
- ```start_give_up_smoking_age``` 开始戒烟年龄 // 选择戒烟时 必传 默认0

###查询膳食情况信息

```
GET /dietary/monitoring/{health_monitoring_id}
```

res

```
{
    "code": 200,
    "data": [
        {
            "type": 0,
            "value": 5
        },
        {
            "type": 1,
            "value": 5
        },
        {
            "type": 2,
            "value": 5
        },
        {
            "type": 3,
            "value": 5
        },
        {
            "type": 4,
            "value": 5
        },
        {
            "type": 5,
            "value": 5
        },
        {
            "type": 6,
            "value": 5
        },
        {
            "type": 7,
            "value": 5
        },
        {
            "type": 8,
            "value": 5
        },
        {
            "type": 9,
            "value": 5
        },
        {
            "type": 10,
            "value": 5
        },
        {
            "type": 11,
            "value": 5
        },
        {
            "type": 12,
            "value": 1
        },
        {
            "type": 13,
            "value": 1
        },
        {
            "type": 14,
            "value": 5
        },
        {
            "type": 15,
            "value": 5
        },
        {
            "type": 16,
            "value": 5
        },
        {
            "type": 17,
            "value": 5
        },
        {
            "type": 18,
            "value": 5
        }
    ],
    "message": "success"
}
```

- ```type``` 0 表示：大米、面粉类、杂粮类;	1 表示：薯类;	2 表示：畜禽肉类;	3 表示：鱼虾类;	4 表示：蛋类或其制品;	5 表示：牛奶及奶制品;	6 表示：大豆类及坚果;	7 表示：新鲜蔬菜;	8 表示：新鲜水果;	9 表示：烹饪油;	10 表示：食盐;	11 表示：饮水;	12 表示：早餐;	13 表示：饮酒类型 0表示喝 1表示从不喝 2表示戒酒;	14 表示：红酒;	15 表示：啤酒;	16 表示：黄酒;	17 表示：白酒;	18 表示：其他酒类;	
- ```value``` 正常情况下数值 ； 当表示是否时 0为false 1为true; 当表示为饮酒类型时 饮酒类型 0表示喝 1表示从不喝 2表示戒酒；喝水时需转换为毫升
-  type 0~11时 value默认0 type 12时默认false 13时默认0 type 14~18时 默认0
选择不喝或戒酒是 喝酒种类不必传


###膳食情况信息添加或更新

```
POST /dietary/monitoring/{health_monitoring_id}
```

req

```
[
    {
        "type": 0,
        "value": 5
    },
    {
        "type": 1,
        "value": 5
    },
    {
        "type": 2,
        "value": 5
    },
    {
        "type": 3,
        "value": 5
    },
    {
        "type": 4,
        "value": 5
    },
    {
        "type": 5,
        "value": 5
    },
    {
        "type": 6,
        "value": 5
    },
    {
        "type": 7,
        "value": 5
    },
    {
        "type": 8,
        "value": 5
    },
    {
        "type": 9,
        "value": 5
    },
    {
        "type": 10,
        "value": 5
    },
    {
        "type": 11,
        "value": 5
    },
    {
        "type": 12,
        "value": 1
    },
    {
        "type": 13,
        "value": 1
    },
    {
        "type": 14,
        "value": 5
    },
    {
        "type": 15,
        "value": 5
    },
    {
        "type": 16,
        "value": 5
    },
    {
        "type": 17,
        "value": 5
    },
    {
        "type": 18,
        "value": 5
    }
]
```

- ```type``` 0 表示：大米、面粉类、杂粮类;	1 表示：薯类;	2 表示：畜禽肉类;	3 表示：鱼虾类;	4 表示：蛋类或其制品;	5 表示：牛奶及奶制品;	6 表示：大豆类及坚果;	7 表示：新鲜蔬菜;	8 表示：新鲜水果;	9 表示：烹饪油;	10 表示：食盐;	11 表示：饮水;	12 表示：早餐;	13 表示：饮酒类型 0表示喝 1表示从不喝 2表示戒酒;	14 表示：红酒;	15 表示：啤酒;	16 表示：黄酒;	17 表示：白酒;	18 表示：其他酒类;	
- ```value``` 正常情况下数值 ； 当表示是否时 0为false 1为true; 当表示为饮酒类型时 饮酒类型 0表示喝 1表示从不喝 2表示戒酒；喝水时需转换为毫升
-  type 0~11时 value默认0 type 12时默认false 13时默认0 type 14~18时 默认0
选择不喝或戒酒是 喝酒种类不必传



###查询身体活动状况信息
```
GET /physical_activity/monitoring/{health_monitoring_id}
```

res

```
{
    "code": 200,
    "data": [
        {
            "type": 0,
            "minutes": 20
        },
        {
            "type": 1,
            "minutes": 20
        },
        {
            "type": 2,
            "minutes": 20
        },
        {
            "type": 3,
            "minutes": 20
        }
    ],
    "message": "success"
}
```

- ```type``` 0 表示：重体力活动;	1 表示：中等强度立体活动;	2 表示：步行运动（包括散步、慢走）;	3 表示：静坐;	
- ```minutes``` 时间，必须转换为分钟 // 默认0

###身体活动状况信息添加或更新
```
POST /physical_activity/monitoring/{health_monitoring_id}
```

req

```
[
    {
        "type": 0,
        "minutes": 20
    },
    {
        "type": 1,
        "minutes": 20
    },
    {
        "type": 2,
        "minutes": 20
    },
    {
        "type": 3,
        "minutes": 20
    }
]
```

- ```type``` 0 表示：重体力活动;	1 表示：中等强度立体活动;	2 表示：步行运动（包括散步、慢走）;	3 表示：静坐;	
- ```minutes``` 时间，必须转换为分钟 // 默认0

###查询睡眠情况
```
GET /sleep/monitoring/{health_monitoring_id}
```

res

```
{
    "code": 200,
    "data": {
        "the_quality_of_sleep": 1,
        "sleep_hour": 3,
        "the_medicine_the_number": 4,
        "more_than_dream": true
    },
    "message": "success"
}
```

- ```the_quality_of_sleep``` 睡眠质量 0 非常好  1 尚好     2 不好  3 非常差 // 默认 0
- ```sleep_hour``` 睡眠时间 // 默认0
- ```the_medicine_the_number``` 服药次数 // 默认0
- ```more_than_dream``` 是否容易做梦或惊醒 // 默认 false


###睡眠情况添加或更新
```
POST /sleep/monitoring/{health_monitoring_id}
```

req

```
{
    "the_quality_of_sleep": 1,
    "sleep_hour": 3,
    "the_medicine_the_number": 4,
    "more_than_dream": true
}
```


- ```the_quality_of_sleep``` 睡眠质量 0 非常好  1 尚好     2 不好  3 非常差 // 默认 0
- ```sleep_hour``` 睡眠时间 // 默认0
- ```the_medicine_the_number``` 服药次数 // 默认0
- ```more_than_dream``` 是否容易做梦或惊醒 // 默认 false

###查看心理状况
```
GET /psychologic_status/monitoring/{health_monitoring_id}
```

res

```
{
    "code": 200,
    "data": {
        "happy_type": 2,
        "hope_type": 2,
        "leave_sadness_type": 2,
        "lonely_type": 2,
        "depressed_type": 2,
        "excited_type": 2,
        "nervous_type": 2
    },
    "message": "success"
}
```

- 心理状况类型 0 完全不符合   1 比较不符合    2 一般 3 比较符合4 完全符合
- 全部默认0
- ```happy_type``` 是否快乐
- ```hope_type``` 是否充满希望
- ```leave_sadness_type``` 是否摆脱悲伤
- ```lonely_type``` 是否孤独
- ```depressed_type``` 是否压抑
- ```excited_type``` 是否情绪激动
- ```nervous_type``` 是否紧张


###心理状况添加或更新
```
POST /psychologic_status/monitoring/{health_monitoring_id}
```

req

```
{
    "happy_type":2,
    "hope_type":2,
    "leave_sadness_type":2,
    "lonely_type":2,
    "depressed_type":2,
    "excited_type":2,
    "nervous_type":2
}
```


- 心理状况类型 0 完全不符合   1 比较不符合    2 一般 3 比较符合4 完全符合
- 全部默认0
- ```happy_type``` 是否快乐
- ```hope_type``` 是否充满希望
- ```leave_sadness_type``` 是否摆脱悲伤
- ```lonely_type``` 是否孤独
- ```depressed_type``` 是否压抑
- ```excited_type``` 是否情绪激动
- ```nervous_type``` 是否紧张

###居住环境情况查询
```
GET /living_environment/information/{health_information_id}
```

res

```
{
    "code": 200,
    "data": {
        "outdoor_the_air_pollution": true,
        "the_number_of_cook": 2,
        "oil_heat": true,
        "ventilation_equipment": 0,
        "no_brush_pot": true,
        "the_remaining_oil_cook": true,
        "immediately_off_lampblack_machine": true,
        "heating_method": 0,
        "indoor_the_air_pollution": true
    },
    "message": "success"
}
```

- ```outdoor_the_air_pollution``` 居住环境是否空气污染 // 默认 false
- ```the_number_of_cook``` 自己做菜次数 // 默认 0
- ```oil_heat``` 油烧热 、、 // 默认false
- ```ventilation_equipment``` 通风设备 0 油烟机   1 排期扇    2 无 // 默认0
- ```no_brush_pot``` 不刷锅 // 默认false
- ```the_remaining_oil_cook``` 油炸后做菜 // 默认false
- ```immediately_off_lampblack_machine```  立马关油烟机 // 默认false
- ```heating_method``` 取暖方式 0 集中供暖   1 燃煤炉 2 其他 / 默认 0
- ```indoor_the_air_pollution``` 室内空气污染 // 默认false

###居住环境情况添加或更新
```
POST /living_environment/information/{health_information_id}
```

req

```
{
    "outdoor_the_air_pollution":true,
    "the_number_of_cook":2,
    "oil_heat":true,
    "ventilation_equipment":0,
    "no_brush_pot":true,
    "the_remaining_oil_cook":true,
    "immediately_off_lampblack_machine":true,
    "heating_method":0,
    "indoor_the_air_pollution":true
}
```


- ```outdoor_the_air_pollution``` 居住环境是否空气污染 // 默认 false
- ```the_number_of_cook``` 自己做菜次数 // 默认 0
- ```oil_heat``` 油烧热 、、 // 默认false
- ```ventilation_equipment``` 通风设备 0 油烟机   1 排期扇    2 无 // 默认0
- ```no_brush_pot``` 不刷锅 // 默认false
- ```the_remaining_oil_cook``` 油炸后做菜 // 默认false
- ```immediately_off_lampblack_machine```  立马关油烟机 // 默认false
- ```heating_method``` 取暖方式 0 集中供暖   1 燃煤炉 其他 / 默认 0
- ```indoor_the_air_pollution``` 室内空气污染 // 默认false
###查询体检信息

```
GET /medical/monitoring/{health_monitoring_id}
```

res

```
{
    "code": 200,
    "data": [
        {
            "type": 11,
            "value": 100
        },
        {
            "type": 0,
            "value": 28
        },
        {
            "type": 1,
            "value": 28
        },
        {
            "type": 2,
            "value": 28
        },
        {
            "type": 3,
            "value": 28
        },
        {
            "type": 4,
            "value": 28
        },
        {
            "type": 5,
            "value": 28
        },
        {
            "type": 6,
            "value": 28
        },
        {
            "type": 7,
            "value": 28
        },
        {
            "type": 8,
            "value": 28
        },
        {
            "type": 9,
            "value": 28
        },
        {
            "type": 10,
            "value": 28
        }
    ],
    "message": "success"
}
```

- ```type``` 0 表示：身高 cm;	1 表示：体重 kg;	2 表示：收缩压 mmHg;	3 表示：舒张压 mmHg;	4 表示：腰围;	5 表示：高密度脂蛋白固醇 mmol/L;	6 表示：低密度脂蛋白固醇 mmol/L;	7 表示：总胆固醇 mmol/L;	8 表示：甘油三酯 mmol/L;	9 表示：空腹血糖 mmol/l;	10 表示：尿酸 umol/L; 11 表示臀围 cm;	
- ```value``` 数值 double类型  // 默认 0.0

###体检信息添加或更新

```
POST /medical/monitoring/{health_monitoring_id}
```

req

```
[
    {
        "type": 0,
        "value": 28
    },
    {
        "type": 1,
        "value": 28
    },
    {
        "type": 2,
        "value": 28
    },
    {
        "type": 3,
        "value": 28
    },
    {
        "type": 4,
        "value": 28
    },
    {
        "type": 5,
        "value": 28
    },
    {
        "type": 6,
        "value": 28
    },
    {
        "type": 7,
        "value": 28
    },
    {
        "type": 8,
        "value": 28
    },
    {
        "type": 9,
        "value": 28
    },
    {
        "type": 10,
        "value": 28
    }
]
```
- ```type``` 0 表示：身高 cm;	1 表示：体重 kg;	2 表示：收缩压 mmHg;	3 表示：舒张压 mmHg;	4 表示：腰围;	5 表示：高密度脂蛋白固醇 mmol/L;	6 表示：低密度脂蛋白固醇 mmol/L;	7 表示：总胆固醇 mmol/L;	8 表示：甘油三酯 mmol/L;	9 表示：空腹血糖 mmol/l;	10 表示：尿酸 umol/L; 11 表示臀围 cm;	
- ```value``` 数值 double类型  // 默认 0.0
